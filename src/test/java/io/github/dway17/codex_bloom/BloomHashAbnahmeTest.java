
package io.github.dway17.codex_bloom;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

/** @author t.richter */
public class BloomHashAbnahmeTest {

	@Test
	void test() {
		IBloomHash bloomHash = BloomHashFactory.getDefault();
		String data;
		// first line is the result for real values with the seeds from the confluence page
		data = "Rieger;Lukas;26.04.1980;M;Trc2t7bXvSa8xJ1H37yx0GAlgUaKSrrbmV5FrvVEjZ5bdBX8eRihtzhUZdY6ngFNXI8MaB8WvkA2uZgRbaAuazTfCV/fydYRGHRMIB8BSjyw0XDyWQMxerZMWlZu8tv0RoX7u3btOSc7qgZ3Q63DIIgwFfGF2AKB3icZ0BX6+ZrgDRHmjRX27X2zORuILExZGBkuXVNkjgv1ijOS/kck2XK5/effB8+/tg1qEUfTBNmh1lquhfM53LbACINwP5I/u78hQF9PDppgls1scE6ePDnNZgO/EnqqWT0LAHJYAZocHoU/vGdT1uII8KbDiebHYzYWVLYbBn+Nrg==\n"
				+ "Moser;Ella;09.08.1962;F;RV0224aVkaSJ2ErPnrjxgXWtCC4p6xGNHZa1D2RKqrQJcCC0dKp1qZneT62+1+RRZe4I/bfNnEnTaIBSZtOOWxvLelZ/CIb0PNze7UnjS/xi0nBwcEBT+Oakz0a426XORuNMnZ6OMVerSE25bjtK5SF8DXGBzFIxpmdZw40QiYzAipdWrE1ov8CCGRnoTjxLlFotKUMhFlttL/OwpaECBjohPexZTy27LuZYmz2mhMWhj3wPYEj/WP9E7H84X5I5P1OAocPKM6409GzoQOc7CP98zpvjiRCavp16TJ5xlbsQJKM3rKsHbmDpyqbTr/bQr8w2zSJgLsYHtg==\n"
				+ "Zeller;Jonas;21.02.1984;M;HA409tCH+eF8Vm+em8BR9DVvhCbITJedqn4ybqaYD5QYcalisBjiteFWd2kqCsV5Xs4UTxd/JOAhEchYe7HvX2jZCRDcKPM7mFT0NP9Dzj7m7DDzzQMWu6fO+kKgWhPOCuJ5G+YPhWAjkBbnEYHgaKxg3g+iUBIp33/I9xsa/ZxgRrFrHRiHz00TkF+s7AuJWVmNkUMm70sv+1Kb7FTbvXyIgcUVLiElbsVeDQWx8NS4Fl++BI3ZmHNEQT5JP9a1C8aaAAlHNBip59UubTUeHCZuXX6Pmakmpe0pwfz6GYuMZpu2pM/DVolpyCPylT/na89d9LBWIf6rqg==\n"
				+ "Kiefer;Nadine;15.08.1993;F;V50kPpL9H/xPqXwjUJq1xzD/JC6LrZGVBtS2D/X7ju4dtAAvYLVGhpTA5pkabE7RYe8OwW13/flVIcL5YpqvWVUvQFxc5NWyRNjdrN6PGj7g2hYyyCI76abQXVYW3QfMB+0d1dxhM5+78DWtYb8EhahcD/plzhkqnvfQkJUoo5pQ6uB2zVlG7B1w/NuNrGzcHZrdCFqHl1uije6W/A8DiLaBGYRTHJNDZt5aRVTygaGD7F+EINHJGdtAGY5xgoL1MB+VsQ3mf6/gglneQFSZGOC+C0uuIZCuoc0LwEj7xVmMJ5M3tlYk/29u6OJaCWChprs7bzZcSo+Ogg==\n"
				+ "Müller;Leonie;20.10.1972;F;n1xgkoRXl/BD0GeOboAx8SckKCzJQ5SvND48TqXaqYYYeSiqMYSWsZNyTPc+bMWNxc5QPxdFN5HCERjia/DHW2K5U9beOoH6jNqHLYipiz4jb5Dg4DtcuWb62ka4uvPODuL93J6iIcY7ad29Yq44bCRku0RqVgMarjOoltxw8YxUw7fqDFgLWRDCXEuEzd2oWcnzOEKVd0spT3u/7lFeHIwQqUn1AgEjYuxTmb31kJQzjb+mTonJXXlAWH5adt+7I5fnMClBtqcl0tW4aN8+dCx+3rf6B4Cegk36hd/aHZoAJAs/ta9SdSNNyJPzgTekSd995iAyK46nrg==\n"
				+ "Krauß;Gisela;08.10.1950;F;PT8kZrq3E7aOaknDnyKo03S/KK9LaD6dkYa4C2VSGM5JoEq8TKQmoNP8Z88+/sZZTzOe6XV/s8gTYJhnKJrg+a+ZpFbbxFHUdfxutE6DT33rLnIS1fKbOLixTk6q64OuRmXF3Q7CCRUoPBe+azsbVLh0PUIN7FlKjmXZl7eMsCBQqtJzyaXirGQAf8J1vDEcGZjFBVMmtjmmqvhYoKEKinIhqf1bSq3mrAVKClD6htfxinoEsEgbWfZq351KetI8MVeo0SFcOiRQ187AYuejEHoU3pFuCICq1V07Rfp4ZHvNIQQ/t88Wd6StxrLardaJXp077T9JD443hA==\n"
				+ "Otten;Theo;15.05.1957;M;dZoGZpDRKPEDdFmjmeEJdBE0T++I4gDVtf85bn0QsJd3JKX9wLiNuTdU42NWbSpSCdMtpFNtM9NIPIqEy93qS+SbAxqZSAMaPBZKNcPTX3IY39cPfmg36IfsTVUO8tNugOaVO0kAuhf6n2R3ArhVPK62xSX7TEfYp60irzVOL/7yu7AnZ5Ks70CT0QQ81YVJkOJBWNG3h2u7u1eRtFIHu8aAd99+K3mV7MZaTh2FCnwB4f83dtH/vwJKjY25KoLrtHaS3iRlPio35sv4J9iFPF/qJQhHDjmoobAImQjdkLFFA16eBg2GcfvfzXDGj+WDJ2AdZBJSSa0foQ==\n"
				+ "Emmerich;Anton;28.10.1958;M;bY51+tfZYfiKbn2y27Wg0mX2BCToOALNtU4eLisQQ91ccW95ifkyjTBmZ2Yr+sFnXacEInd3EkGjJcgop7kmnvAbg1TaCBFblBTktBqv2h2iBfaMaJ+x/3NvhRdoatuuTGOQC9oFuT44vJbngqV7Vm42Ku+uYEqAj7/hN1ieL+4g4VSihLM/6czj18s6/33sWfDJ1fE0Zisn2XY0pJRsH1yHg4vFDgPlZq5vjtDu4pExhn6ltq4i13cI0VwIEhImAneQNQPFHPoQhcOoApGKEIYsbn+9qOng8f2LAix5qWIcIgqyLEEnVur16Lb213/ibVNbpCZSLWwO3w==\n"
				+ "Mende;Pia;24.01.1995;F;OXw8QJ46QX+NcEi6YiHR0XTXaH3JK1SdHYS5T2h60I9FJEQkiOWNgZLUS7Y/b4fZTT+VXA9F+tqTeKhrLo+KGfc9ohGeHRVsPdzO/EtDz3QBmnYZ4uOZqyK3wVW5+8uMXOPwnboGIR0/GrM6Sr8OVaiURlypV8qTjiNRE7+qouiMy5M3VYXSqsSCmStE9nQ9EftlCXJlomE+j/65lEgEjh6GpavYHIq3Zt1aR7m+iN2BtH4CUslSH/Zn7R34jvo3MlOwUAGEKE4Uomn6YuQrOC46/7vqASH0kje7VR5+pfkIIcMphYgyfv+d8pDql+bJjiE/bRJKL+UgjQ==\n"
				+ "Seeger;Julian;18.03.1953;M;bTZ2NdZX+LqOFgjV3+CyxmQ1gURsGhTfn1w5Lm5TavY9+HF8IbnKpXDUTHKaMutETmeubVtnOtFWmYAJn5FkfqQbCR5scEdY1FbssfmjWl3+2ja2eFW5+ytOAlbLy8vkC8SrHZ93izUvoOA3AbDDpm1URF3MZkyA3DfRu0YaN4CqrxDntZ01qVzlsw2u7litXNgdVEElpFslq3eQ9l4Vkmylqc2jX5//NoxcWQD7NpnBJj4vJau6GNYMxOcgK4JiGKe4ZBAUPPy9gckgfZybHhscVb2PAGtqVzHoXXJ5lbgIMrKSpmn3/uu64zbXJSXBabASNQAYB28iSg==\n"
				+ "";
		// the second line is for seed=0 uncomment next line, if BloomHashFactory.properties contains real values
		data = "Rieger;Lukas;26.04.1980;M;incLXfQ+ahOZAJOGyZOFpVxP5nFuYLYme+3i7UYWol5mwAYcO0JuoCD5zzUF2aJ31VR/GN21WT+Be1Eh0iG6Ky/P4y9wRuwKhViO1k/J5rz8LZaT1KqS876VZh/WwVshqWmQF11r4lY+GOIG+a3BU2FDW/ZQTt4ZhCuaxrT3sz2Jv2JgaCIVpeJOD+hc+Ictn8aq4FvbLYMRcYWeg3mfrBuZj9VuEtXHxxgziCqZ8DH6YcIER1xir2nuYfseHUU6HdmDycborHgmNrDh+CpO+AlY1Ndg+s7QyD3IidrpLN6dqfJ3BSu6/MECmyeMyVfgRd0Iq8PHhdAcgA==\n"
				+ "Moser;Ella;09.08.1962;F;judYRa+qSkYcBOLmcbpxzc+R8BxGbHryKakML8VzmztFlzu3GURSrZAxPazEcYI7lXzXBEUz4zVZ0eBlnGhQA4efgrNBe+hCl1Rk3C7BQolJIxJEkmCLBrNKbqu/i4eEeDxaNzLJhuZMNMHfA57uYzTtTXZ3m1OUc4pet5pvlbld70vl2MmAxdoPbx7e8CRptca7vurjCZMA0QhfS+2XaC9LZ48Xplvhkv8BRSjZvTf3cq7n7AYisSH0SRhYjm9t0jtLqcrx5XKFNkDikhzkEABx9HYm2GfRmr/sTz/NLsixkndfdT+hSMmS/PCAaO7VVFUJvZnaUyiswA==\n"
				+ "Zeller;Jonas;21.02.1984;M;j2cfGT67YkqPJVseS5sWON8SAXBuYJIrPcHor1YX8h5ANBM2iXJNvZFrgb3m84JnFV7XCMAxSDOXO1ETsOEyI6uNQzGgG8zCo1yu1vbEdqD8/dITxCQz15uEThfrgDoAoNSoNRNjdsJEnPgOjGbB+2BjW26p3tKzFCuW5jPfMy3RFWLDlCiwb8ttDGhEeEV91c6r3UhnCHEYkf/7i8+W5ovbjr+PplbH298xyG6DZ52I4c61ZsFmfWFuETtuOQU6F7GHgU9oD/gUMFBr+ioMr8hR9vcq+i4087uYzXtMHPwcjNd1VSuokufCynuE6Vb/R/cEqYCLC4Ispg==\n"
				+ "Kiefer;Nadine;15.08.1993;F;tf8gyei+SjZ+KRO5MpX0iV5XlTzujFg+aE0mB0i3Hh+kYIMGAl5+xKDoPXHhsfDzeXiDBeGSzXVYNcAzFe0yIsfMEhvSd+FCudvOiqagQmxtC9VzjKLrF/+Izq/82T+DRAYOVDnc3hYEPBG3AorBryR0y/L2RpnOX5IEz5jdlGNT72LDuMPir/x/A4g+97E5V8eDnt9aPdMTVGrbC+GYLA07QmUMoA711+qxrErIvI//4Io7czRsmCE0VZzxjcfTlHWric7onOJVHhnnO1HECAj2vHon+ZZQWfEtSn8ELsZ7rtLlT+SBIIlykMAY7rv5RXQJjIvvyGjopA==\n"
				+ "Müller;Leonie;20.10.1972;F;i/8cEWuqKk9UJULGIbrl+O8AxXdaHlK6fyAsL9Q3kj4A8hM0GDxMlaDzva3H84IjFXjXCtGx2XFd8dEn0OISM6eOEJlAW+xGEdkMHq+M0irdFNcznKCKNqIZRiV1qhKAaMaYFbvL1SJWNFmPis7E56RpV/KqOpNyR4tW/preA5lJV+rHjMEkm9Z+ZkjY8DX5RcazlmrjGfFBVwP/Tu/XpL9yjpQbpmv1ktwxxGDBfhfjcoYnVxhm8XMGCxHHLwVyW7HJ2c7hjnIGPBBqoRyMOQJ39PYu2yZB27fQj57fTKwppcN1XfmD8M1g/vEI6Xb95+AruoeD00g98A==\n"
				+ "Krauß;Gisela;08.10.1950;F;J+Ea7SLu4kZOpma+a5JwzZ4bKA329jxaSWoMmsEnGj/mSL81mRJ+5ZS19GQB+6JxBfXWQFki87EcEP2Bt9kUocOVOLswe+xCpT7+WSXEop9po1dQweVzL7sqYqc/4Qb2bPxJHz3/whbkHInnApz68xzllv7W+pOgg5Jeh5vcsOgF5zbjYNXJdFhKapzW8MY5zcYIaxKijQcdEXGLW9yEOp5fZXw2Q8pByf8rdAKJnY/uwqQ20jHmWym3Q60ZIE15JiebQtrg/MIe4VvioACQFUpwfFQkMWddxXetT3dsc8atDBp3Jbar0eky3aOoqIux8EY5I5jUwUqszA==\n"
				+ "Otten;Theo;15.05.1957;M;O+MY3ZCy/1jVhH5WJ9hH3dR4/CZOutpf0d1pufdjECUo3IOUUZDrgSL8rOXB0+NJW0GygGhU2f5Vv9WpFCNyqy0aqzgwUqFPAGj81K3A56f8OPqAFRtj/a6c5+7BmS7MLXK2R8npZJbS1FwXIpoZdk4nynvUt1OSEQES5tmuMsx/h/MBTRq17cDPH2gOex24QeMpP6uKDEM6G8qojyAALPIgQ+xI5H9YkpMGsEIV/WXiedZHVFciN1NnZfN3nKCZf19xIfRrj0sM8NDDszRq3DyG2DRyc8K0vOPE2tKcacy/jMYkymPhxoCQcGLT+ve1U54OjcjMR/Ac0g==\n"
				+ "Emmerich;Anton;28.10.1958;M;tvMMERRrwgTFJUZH0PRT/hyWvcf+5toGBeRJrdK3Iihm4ncMHDRjgGIrtLSG1VdzQfUYQuhx24ZVYaFbtOC6eSzdI7ggF+BKp7i+3unHoifchNcV4I0jvqnGTwbSkRrAJVKeJd8pPAyknMuHK2zyYX5DN3KIkDX1JdkWtuTckzxRH9RmoIbC5qquD8neGEjoW9SreWq6nbU32YLZX+6cMog7lOxeI1P0sx4hZE6PfJWC4cD0xpZ8t3meYiCXlJ/+VrXQweZlv1DnxGpBwhAMfQhT2Td48a7pU7fOy/upVMo+nPt31Sb7Vgzi1uqN0dqkU9asLINRSzAswg==\n"
				+ "Mende;Pia;24.01.1995;F;9bHMzSnzyU/AgGTjSZvlvabQ6KfGujBaSa88iVUjUu4k5toXMdBmmab1pW1IU4J9dzErFqGS8eAI4LCl02AwYUOeqhM4tuXuszb83YzOgtxIIXZAsUz5WveeaqyX2QLdRJT6lymfVwmw9NlX8ov6xAZcKbe4LHKIn4AXn4vzpNwTdzZmuNBCzdx/ZmzS2qM9R+SQummqi5cxOZvQTvDK+q54y6R07+sx4+h5DQJDfmeeYCClX0O6YQmhQRhzL6P9k7ywqFblzFMlnDHjG9XGEhvjfEx0YW8JQvNjjfKPNcQrhNp2WabB1A0i9hicqq+1wMpRPdely5uEEA==\n"
				+ "Seeger;Julian;18.03.1953;M;P+9LWXSiTGe5hn8349qizd+RtzW25jp/L7TdpMYj0gxMqpqfDlbXoWN5hHKHybxKN3Q6Wv0+yJ9UFdElEbCaqyga5wO7LsJKBZms3v+MYyVY3oUxlI1yHvqW7uf7mU5CLVC6NZ1k9BLcVeSJp6Pk834pvXfRvoODh4PHtoV/CFxbn+MkZAYGDcdZAWzEeIupP8RrvbnqnJIRm7PQgW0wfKAZZfTKlDPQ2wzDNBKbvwDIIUoU59U6M2IvJLCRFMPTF5kX4UJhjsIlBpvhMSReFyhS+O9nHO4pSL3My3oqhuxejNIy5ya6wony9g4IwzuAUVuMicfV53JcwA==\n"
				+ "";
		String[] lines = data.split("[\n\r]+");
		for (String line : lines) {
			System.out.println(line);
			String[] fields = line.split(";");
			String[] dateStrings = fields[2].split("\\.");
			String lastName = fields[0];
			String firstName = fields[1];
			char gender = fields[3].charAt(0);
			int birthYear = Integer.parseInt(dateStrings[2]);
			int birthMonth = Integer.parseInt(dateStrings[1]);
			int birthDay = Integer.parseInt(dateStrings[0]);
			String actual = bloomHash.createBase64Result(firstName, lastName, gender, birthYear, birthMonth, birthDay);
			System.out.println("" + firstName + " " + lastName + " " + gender + " " + "");
			System.out.println("actual  =" + actual + "");
			String expected = fields[4].replaceAll("\\\\n", "");
			System.out.println("expected=" + expected + "");
			assertEquals(expected, actual);
		}
	}


}
