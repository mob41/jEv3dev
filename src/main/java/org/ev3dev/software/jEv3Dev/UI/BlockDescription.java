package org.ev3dev.software.jEv3Dev.UI;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlockDescription {
	
	public static final BufferedImage DEFAULT_ICON = getDefaultImageFromClass();
	
	public final String DEFAULT_SHORT_NAME = "UBK" + this.hashCode();
	
	public final String DEFAULT_NAME = "UntitledBk-" + this.hashCode();
	
	public final String DEFAULT_AUTHOR = "(Unknown)";
	
	public final String DEFAULT_VERSION = "(Unknown)";
	
	public final String DEFAULT_DESC = "An untitled block.";
	
	private final String shortName;

	private final String name;
	
	private final String author;
	
	private final String version;
	
	private final String description;
	
	private final BufferedImage icon;
	
	private static BufferedImage getDefaultImageFromClass(){
		try {
			return ImageIO.read(
					Block.class.getResourceAsStream(
							"/org/ev3dev/software/jEv3Dev/UI/resources/block.fw.png"
							));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public BlockDescription(){
		this.name = DEFAULT_NAME;
		this.shortName = DEFAULT_SHORT_NAME;
		this.author = DEFAULT_AUTHOR;
		this.version = DEFAULT_VERSION;
		this.description = DEFAULT_DESC;
		this.icon = DEFAULT_ICON;
	}
	
	public BlockDescription(String name, String author, String version, String description){
		this.name = name;
		this.shortName = name.trim();
		this.author = author;
		this.version = version;
		this.description = description;
		this.icon = DEFAULT_ICON;
	}
	
	public BlockDescription(String name, String author, String version, String description, BufferedImage icon){
		this.name = name;
		this.shortName = name.trim();
		this.author = author;
		this.version = version;
		this.description = description;
		this.icon = icon;
	}
	
	public BlockDescription(String name, String shortName, String author, String version, String description, BufferedImage icon){
		this.name = name;
		this.shortName = shortName.trim();
		this.author = author;
		this.version = version;
		this.description = description;
		this.icon = icon;
	}
	
	public String getShortName(){
		return shortName;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public String getVersion(){
		return version;
	}
	
	public String getDescription(){
		return description;
	}
	
	public BufferedImage getIcon(){
		return icon;
	}
}
