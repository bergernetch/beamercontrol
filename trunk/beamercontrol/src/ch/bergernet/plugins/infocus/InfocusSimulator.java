package ch.bergernet.plugins.infocus;

public class InfocusSimulator {

	int powerState = PowerState.POWER_ON;
	int freezeState = 0;
	int source = InputSelector.INPUT_PC1;
	String beamerModel = "Beamer Simulator 0.0.1";

	public String getInfo(){
		return beamerModel;
	}
	
	public String sim(String cmd) {

		String result = cmd;
		if (cmd.equals("(FRZ1)")){
			freezeState = 1;
		}
		if (cmd.equals("(FRZ0)")){
			freezeState = 0;
		}
		
		if (cmd.equals("(PWR1)")){
			powerState = 1;
		}
		if (cmd.equals("(PWR0)")){
			powerState = 0;
		}
		
		if (cmd.contains("(SRC")){
			source = Integer.parseInt(cmd.substring(4, 5));
		}
		
		if (cmd.equals("(PWR?)")){
			result = "(PWR?)(0-1," + powerState + ")";
		}
		
		if (cmd.equals("(SRC?)")){
			result = "(SRC?)(0-6," + source + ")";
		}
		
		if (cmd.equals("(FRZ?)")){
			result = "(FRZ?)(0-1," + freezeState + ")";
		}
		if (cmd.contains("<pcml><Stats><Model/></Stats></pcml>")){
			result = beamerModel;
		}
		if (cmd.contains("cli all")){
			result = "CMD  Value       Minimum     Maximum     Increment  ";
			result = result.concat("---- ----------- ----------- ----------- -----------");
			result = result.concat("ACE            0           0           1           1");
			result = result.concat("AIM            0           0           1           1");
			result = result.concat("APO            0           0           1           1");
			result = result.concat("ARZ            2           0           2           1");
			result = result.concat("ASC            1           0           1           1");
			result = result.concat("BAL           50           0         100           1");
			result = result.concat("BAS           50           0         100           1");
			result = result.concat("BCG           50           0         100           1");
			result = result.concat("BLK            0           0           1           1");
			result = result.concat("BRT           50           0         100           1");
			result = result.concat("BSC            0           0           2           1");
			result = result.concat("CAD            0           0           1           1");
			result = result.concat("CEL            0           0           1           1");
			result = result.concat("CLR           50           0         100           1");
			result = result.concat("CSM            3           0           3           1");
			result = result.concat("CST            4           0           6           1");
			result = result.concat("CON           50           0         100           1");
			result = result.concat("DHP            1           0           1           1");
			result = result.concat("DKH           50           0         100           1");
			result = result.concat("DKV           51           0         100           1");
			result = result.concat("DMG            1           0           1           1");
			result = result.concat("DSC            1           0           7           1");
			result = result.concat("DSU            1           0           2           1");
			result = result.concat("EFK            7           0          10           1");
			result = result.concat("ERR            0           0           0           1");
			result = result.concat("FRZ            " + freezeState + "           0           1           1");
			result = result.concat("GT1          192           0         255           1");
			result = result.concat("GT2          168           0         255           1");
			result = result.concat("GT3          111           0         255           1");
			result = result.concat("GT4            1           0         255           1");
			result = result.concat("GCG           50           0         100           1");
			result = result.concat("HPS          293           0         343           1");
			result = result.concat("ILK            0           0           1           1");
			result = result.concat("INT            0           0           1           1");
			result = result.concat("IP1          192           0         255           1");
			result = result.concat("IP2          168           0         255           1");
			result = result.concat("IP3          111           0         255           1");
			result = result.concat("IP4           74           0         255           1");
			result = result.concat("IRE            1           0           1           1");
			result = result.concat("KBE            1           0           1           1");
			result = result.concat("LAN            2           0          12           1");
			result = result.concat("LB1            0           0        2000           1");
			result = result.concat("LB2            0           0        2000           1");
			result = result.concat("LB3            0           0        2000           1");
			result = result.concat("LB4            0           0        2000           1");
			result = result.concat("LB5            0           0        2000           1");
			result = result.concat("LML            0           0           1           1");
			result = result.concat("LMP            0           0        2000           1");
			result = result.concat("LMT            0           0       65535           1");
			result = result.concat("LPE            0           0           1           1");
			result = result.concat("LOD            0           0           1           1");
			result = result.concat("LRT            0           0           1           1");
			result = result.concat("MNU            0           0           1           1");
			result = result.concat("MSK            0           0        1000           1");
			result = result.concat("MSS           16           0         100           1");
			result = result.concat("MTE            0           0           1           1");
			result = result.concat("MTS         1344        1216        1472           1");
			result = result.concat("NAV            0           0           4           1");
			result = result.concat("NM1          255           0         255           1");
			result = result.concat("NM2          255           0         255           1");
			result = result.concat("NM3          255           0         255           1");
			result = result.concat("NM4            0           0         255           1");
			result = result.concat("PHP          608           0        2000          21");
			result = result.concat("PIZ            0           0           3           1");
			result = result.concat("PVP           20           0        2000          21");
			result = result.concat("PNH          512         512         512           1");
			result = result.concat("PNV          384         384         384           1");
			result = result.concat("PST            0           0          12           1");
			result = result.concat("PSV            0           0           1           1");
			result = result.concat("PWR            " + powerState + "           0           1           1");
			result = result.concat("RVI            0           0           1           1");
			result = result.concat("RCG           50           0         100           1");
			result = result.concat("REA            0           0           1           1");
			result = result.concat("RST            0           0           1           1");
			result = result.concat("SHP            2           0           4           1");
			result = result.concat("SRC            " + source + "           0           6           1");
			result = result.concat("SR1            1           0           6           1");
			result = result.concat("SR2            4           0           6           1");
			result = result.concat("SR3            1           0           6           1");
			result = result.concat("SR4            2           0           6           1");
			result = result.concat("SSV            0           0           1           1");
			result = result.concat("STA            0           0       65535           1");
			result = result.concat("SCT            0           0           1           1");
			result = result.concat("SVN            0           0           1           1");
			result = result.concat("SWV            0           0           0           1");
			result = result.concat("TMP            1           0           4           1");
			result = result.concat("TNT           50           0         100           1");
			result = result.concat("TRB           50           0         100           1");
			result = result.concat("US1            0           0           1           1");
			result = result.concat("US2            0           0           1           1");
			result = result.concat("US3            0           0           1           1");
			result = result.concat("VAD            0           0           1           1");
			result = result.concat("VOL            0           0         100           1");
			result = result.concat("VPS           35           0          85           1");
			result = result.concat("ZOM         1024           0        1024           1");
			result = result.concat("---- ----------- ----------- ----------- -----------");
		}
		return result;
	}
}
