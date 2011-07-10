package me.zephyr.Config;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Config extends JavaPlugin{
	static String mainDirectory="plugins/config";
	static File configfile=new File(mainDirectory+File.separator+"config.yml");
	static Properties prop=new Properties();
	Logger log=Logger.getLogger("Minecraft");
	String Hello=null;

	@Override
	public void onDisable() {
		log.info("Config shutted down.");
	}
	
	public void loadProcedure(){
		try {
			//Create a fileinputstream to read values from the file
			FileInputStream in = new FileInputStream(configfile);
			try {
				//Load the file which has the values
				prop.load(in);
				//Load in the values what you want
				Hello=prop.getProperty("Hello");
			} catch (IOException e) {
				// Failed to load the file so print error.
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			//File not exist,so print error.
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnable() {
		log.info("Config booting up...");
		//Create directory
		new File(mainDirectory).mkdir();
		//Check file,if it isn't exist then create it and put the default properties,else load the properties from it.
		if(!configfile.exists()){
			try{
				log.info("Creating config file(s)...");
				//Create the file
				configfile.createNewFile();
				//Create the outputstream and put default values to the file
				FileOutputStream out=new FileOutputStream(configfile);
				prop.put("Hello", "World");
				prop.store(out, "Do NOT change this config file!");
				//Close the file
				out.flush();
				out.close();
			}catch(IOException ex){
				//Failed to create file,output the error
				ex.printStackTrace();
			}
		}else{
			log.info("Loading config file(s)...");
			loadProcedure();
		}
		log.info("Config booted up!");
		//Print out the value of the "Hello" string if we successfully loaded it
		if(Hello!=null){
			log.info("Config - Value of 'Hello' is: " + Hello);
		}else{
			log.info("Config - Failed to load 'Hello's value.");
		}
		//And we're done.Have fun with your own one!
	}

}
