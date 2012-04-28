
package net.voiceter; 
 
public class Contact {
 
    //private variables
    int _id;
    String _name;
    String _tags;
    String _voice;
 
    // Empty constructor
    public Contact(){
 
    }
    // constructor
    public Contact(int id, String name, String _tags, String _voice){
        this._id = id;
        this._name = name;
        this._tags = _tags;
        this._voice	 = _voice;
    }
 
    public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_tags() {
		return _tags;
	}
	public void set_tags(String _tags) {
		this._tags = _tags;
	}
	public String get_voice() {
		return _voice;
	}
	public void set_voice(String _voice) {
		this._voice = _voice;
	}
	// constructor
    public Contact(String name, String _phone_number){
        this._name = name;
        this._tags = _phone_number;
    }
   
}