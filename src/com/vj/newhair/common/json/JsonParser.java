package com.vj.newhair.common.json;

import org.json.JSONException;

import com.vj.newhair.common.json.FindHairParser.HairResult;
import com.vj.newhair.common.net.ActionOfUrl.JsonAction;

public class JsonParser {
	
	synchronized public static JsonResult parse(String response, JsonAction act) throws JSONException {
		switch(act){
		case FINDHAIR:
			return getFindHair(response);
		case ZONEALL:
//			return getZoneAll(response);
		case HAIR_COMMENT:
//			return getHairComment(response);
		default:
			return null;
		}
	}
	
	public static HairResult getFindHair(String json) throws JSONException{
		FindHairParser parser= new FindHairParser();
		return (HairResult) parser.parse(json);
	}

}
