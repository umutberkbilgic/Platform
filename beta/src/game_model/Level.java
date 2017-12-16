package game_model;

/*
 * Authors: Doruk Cakmakci
 * 			Umut Bilgic
 * 			Anil Erken
 * 			Arda Atacan Ersoy
 * Desc   : Level class is  ?? tamamlanacak
 */

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import view.SpaceOut;

public class Level {
    
    private TiledMap map;
    
    //a list of all characters present somewhere on this map
    private ArrayList<Character> characters;
    private Player player;
    private Block[][] blocks;
    public int startX;
    public int startY;
    public int deathY;
    
    public int finishX;
    public int finishY;
   
    
    public Level(String level, Player player) throws SlickException
    {
        map = new TiledMap("data/levels/" + level + ".tmx", "data/img/");
        characters = new ArrayList<Character>();
        
        this.player = player;
        addCharacter(player);
        
        loadBlockMap();
    }
    
    private void loadBlockMap(){
        //create an array to hold all the Blocks in the map
        blocks = new Block[map.getWidth()][map.getHeight()];
        
        int layerIndex = map.getLayerIndex("CollisionLayer");
        
        if(layerIndex == -1){
            //TODO we can clean this up later with an exception if we want, but because we make the maps ourselfs this will suffice for now
            System.err.println("Map does not have the layer \"CollisionLayer\"");
            System.exit(0);
        }
        
        //loop through the whole map
        for(int x = 0; x < map.getWidth(); x++){
            for(int y = 0; y < map.getHeight(); y++){
                
                //get the Block
                int BlockID = map.getTileId(x, y, layerIndex);

                Block block = null;
                
                // determine what kind of block this is
                switch(map.getTileProperty(BlockID, "tileType", "solid"))
                {
                
                case "air":
                    block = new AirBlock(x, y);
                    break;
                default:
                    block = new SolidBlock(x, y);
                    break;
                }	
                
                if (BlockID == 24) 
                {
                	block = new FinishBlock(x, y);
                	finishX = x;
                	finishY = y;
                }
                
                if (BlockID == 33 || BlockID == 34)
                	block = new WaterBlock(x, y);
                
                else if (BlockID == 23){
                	startX = x;
                	startY = y;
                	
                	block = new StartBlock(x, y);
                } 
                
                else if (BlockID == 22)
                	block = new DeathBlock(x, y);
                
                blocks[x][y] = block;
            }
        }
    }
    
    public Block getFinishBlock()
    {
    	return blocks[finishX][finishY];
    }
    
    public void addCharacter(Character c)
    {
        characters.add(c);
    }
    
    public ArrayList<Character> getCharacters()
    {
        return characters;
    }
    
    public Block[][] getBlocks()
    {
        return blocks;
    }
    
    public int getXDelta()
    {
    	int xDelta = 0;
    	int half = (int) ((SpaceOut.WIDTH / SpaceOut.SCALE) / 2);
    	int max = (int) ((map.getWidth() * 32) - half);
    	
    	if(player.getX() < half)
    	{
            xDelta = 0;
        }
    	else if(player.getX() > max){
    		
            
            xDelta = max - half;
        }
    	else
    	{
           
            xDelta = (int) (player.getX() - half);
        }
 
        return xDelta;
    }
    
    public int getYDelta()
    {
        int yDelta = 0;
 
        int half = (int) ((SpaceOut.HEIGTH / SpaceOut.SCALE) / 2);
 
        int max = (int) (map.getHeight()*32)-half;
 
        if(player.getY() < half)
        {
            yDelta = 0;
        }
        else if(player.getY() > max)
        {
            yDelta = max - half;
        }
        else
        {
            yDelta = (int) (player.getY() - half);
        }
 
        return yDelta;
    }
    
    public void render()
    {
    	int xDelta = getXDelta();
        int yDelta = getYDelta();
        
        float x = player.getX();
        float y = player.getY();
 
        //render the map first
        map.render(-(xDelta%32), -(yDelta%32), xDelta/32, yDelta/32, 33, 19);
 
        //and then render the characters on top of the map
        for(Character c : characters){
            c.render(xDelta, yDelta);
        }
    }

}