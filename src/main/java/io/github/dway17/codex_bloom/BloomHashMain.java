package io.github.dway17.codex_bloom;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.csv.*;

public class BloomHashMain {

	private static IBloomHash bloomHash;

	public static void main(String[] args) throws IOException {
		bloomHash = BloomHashFactory.getDefault();
		switch (args.length) {
			case 6:
				String firstName = args[0];
				String lastName = args[1];
				char gender = args[2].charAt(0);
				int birthYear = Integer.parseInt(args[3]);
				int birthMonth = Integer.parseInt(args[4]);
				int birthDay = Integer.parseInt(args[5]);
				System.out.println(
						bloomHash.createBase64Result(firstName, lastName, gender, birthYear, birthMonth, birthDay));
				break;
			case 0:
				readAllCsvs();
				break;
			default:
				System.err.println("firstName, lastName, gender, birthYear, birthMonth, birthDay");
				break;
		}
	}

	private static void readAllCsvs() throws IOException {
		Path currentRelativePath = Paths.get("");
		String glob = "*.{csv,tsv,txt}";
		System.out.println("scanning in directory" + currentRelativePath.toAbsolutePath() + " for " + glob);
		PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(currentRelativePath, pathMatcher::matches)) {
			dirStream.forEach(path -> {
				try {
					process(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}

	}

	private static Object process(Path path) throws IOException {
		System.out.println("path='" + path + "'");
		Reader in;
		in = Files.newBufferedReader(path);
		String pathOutS = path.toAbsolutePath().toString().replaceAll("(.*)\\.(...)$", "$1.out.$2");
		Path pathOut = Path.of(pathOutS);
		CSVFormat format = CSVFormat.TDF
//	      .withHeader(HEADERS)
				.withFirstRecordAsHeader();
		CSVParser parser = format.parse(in);
		List<String> header = parser.getHeaderNames();
		if (header.size() != 5 || !header.contains("id_covidom") || !header.contains("Vorname")
				|| !header.contains("Name") || !header.contains("Geb.datum") || !header.contains("Geschlecht")) {
			System.err.println("skipping '" + path + "' because of headers: " + header + "");
			return null;
		}
		Iterable<CSVRecord> records;
//		records = format.parse(in);
		records = parser.getRecords();
		String[] HEADERS = { "id_covidom", "bf" };
		Writer out = null;
		CSVPrinter printer = null;
		for (CSVRecord record : records) {
			if (out == null || printer == null) {
				out = Files.newBufferedWriter(pathOut);
				printer = new CSVPrinter(out, CSVFormat.TDF.withHeader(HEADERS));
			}
			System.out.println(record);
			String id_covidom = record.get("id_covidom");
			String vorname = record.get("Vorname");
			String name = record.get("Name");
			String geschlecht = record.get("Geschlecht").replaceAll("['\"]+", "");
			String gebDatum = record.get("Geb.datum");
			char g = geschlecht.charAt(0);
			DateTimeFormatter fmter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
			LocalDate bd = LocalDate.parse(gebDatum, fmter);
			String bh = bloomHash.createBase64Result(vorname, name, g, bd);
			printer.printRecord(id_covidom, bh);
			printer.flush();
			System.out.println("bh " + bh + " ");
		}
		try {
			printer.close(true);
		} catch (Exception e) {
		}
		in.close();
		return null;
	}


}
