package net.voiceter;
public class Model {

	private String pos;
	private String name;
	private String tags;

	public Model(String name, String tags, String voice) {
		this.pos = name;
		this.name = tags;
		this.tags = voice;
	}

	public String getName() {
		return pos;
	}

	public void setName(String name) {
		this.pos = name;
	}

	
	public void setVoice(String URL){
		this.tags = URL;
	}
	
	public String getInfo(){
		return this.pos+"|Author: " + this.name + "\nTags:[" + this.tags + "]|";
	}
}
