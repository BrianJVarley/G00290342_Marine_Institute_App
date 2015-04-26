package ie.gmit.computing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.media.Image;

public class Node implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Node parent;
	private List<Node> children = new ArrayList<Node>();
	private Image image = null;

	public Node(String name, Node parent) {
		super();
		this.name = name;
		this.parent = parent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Node getParent()
	{
		return this.parent;
	}
	
	public void setParent(Node parent)
	{
		this.parent = parent;
	}
	
	public void addChild(Node child)
	{
		this.children.add(child);
	}
	
	public void insertChild(Node next, Node Existing)
	{
		if(children.size() > 0)
		{
			for (Node child : children)
			{
				child.setParent(Existing);
				Existing.addChild(child);
			}
			this.children = null;
		}
		this.children.add(next);
		next.setParent(this);
	}
	
	public void removeChild(Node child)
	{
		this.children.remove(child);
	}
	
	public Node[] children()
	{
		if (this.hasChildren())
		{
			Node[] temp = new Node[children.size()];
			for (int i=0; i< temp.length; i++)
			{
				temp[i] = children.get(i);
			}
			return temp;
		}
		return null;
	}
	
	
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public boolean hasChildren()
	{
		return this.children.size()>0;
	}

	public boolean isLeaf()
	{
		if(this.children.size() > 0)
		{
			return false;
		}
		return true;
	}
	
	public boolean isRoot()
	{
		if (this.parent == null)
		{
			return true;
		}
		return false;
	}
	
	
	public boolean hasImage()
	{
		return this.image != null;
	}
	
}
