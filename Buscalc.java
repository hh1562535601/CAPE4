package buscalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Scanner;

public class Buscalc {
	
	private	static int BIT_ARRAY_LENGTH = 10*1000;
	private HashMap<String, Integer> map;
	//private HashMap<Integer, mapNode> maps[];
	private List<mapNode> lists[];
	private byte matrix[][];
	private boolean matrixLine[][];
		
	public static void main(String[] args) throws FileNotFoundException 
	{
		//System.out.print("Hello,world!!");
		/*File file=new File("E:\\buscalc\\src\\buscalc\\buslines.dat");
		
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        
        FileInputStream fis=new FileInputStream(file);
		Scanner reader=new Scanner(fis);
		reader.nextLine();
		
		//while(reader.hasNext())
		{
			//int i=reader.nextInt();
			//System.out.print(i);
			String str=reader.nextLine();
			String arry[]=str.split("\\s+");
			//int i = Integer.parseInt(arry[0]);
			System.out.print(arry[2]);		
		}
		byte ba[] = new byte[10000 * 80000];
		Scanner reader = new Scanner(System.in);
		
		reader.next();
		System.out.print(4 >> 2);*/
		Buscalc bc = new Buscalc();
		//byte bf[][] = new byte[80000][10000];
		
		bc.constructMatrix("E:\\buscalc\\src\\buscalc\\buslines.dat");  //read file and construct matrix
		System.out.println("after construct");
		//bc.calculate();
		//System.out.println("after calculate");
	}
	
	public Buscalc()
	{
		int i, j;
		map = new HashMap<String, Integer>();
		//matrix = new byte[80000][10000];
		//lists = new List[3000];  
		
		/*for(i = 0; i < 3000; i++)
		{
			lists[i] = new ArrayList<mapNode>();
		}
		
		matrixLine = new boolean[3000][3000];
		
		for(i = 0; i < 3000; i++)
		{	
			for(j = 0; j < 3000; j++)
			{
				matrixLine[i][j] = false;
			}
		}*/
	}
		
	public void constructMatrix(String filename) throws FileNotFoundException
	{
		int i = 0, id = 0;
		File file=new File(filename);
				
		if(!file.exists()||file.isDirectory())
		    throw new FileNotFoundException();       	
		        
		FileInputStream fis=new FileInputStream(file);
		Scanner reader=new Scanner(fis);
				
		reader.nextLine();
				
		while(reader.hasNext())
		{
			String str=reader.nextLine();
			String arry[]=str.split("\\s+");
					
			int xid = Integer.parseInt(arry[0]);
			int pm = Integer.parseInt(arry[1]);
			int kind = Integer.parseInt(arry[3]);
			String zhan = arry[2];
					
			//mapNode mn = new mapNode(pm, kind, zhan);							
				
			//lists[xid].add(mn);			
			
			if(!map.containsKey(zhan))
			{
				map.put(zhan, i);
				i++;
			}	
		}
		
		/*System.out.println(lists.length);
		for(i = 0; i < lists.length; i++)
		{
			for(j = 0; j < lists.length; j++)
			{
				for(k = 0; k < lists[i].size(); k++)
				{
					for(l = 0; l < lists[j].size(); l++)
					{
						if(lists[i].get(k).zhan == lists[j].get(l).zhan)
						{
							matrixLine[i][j] = true;
						}
					}
				}
			}
		}*/
}
	
	/*public void constructMatrix(String filename) throws FileNotFoundException
	{	
		//File file=new File("E:\\buscalc\\src\\buscalc\\buslines.dat");
		int i = 0, j, id = 0;
		//HashMap<Integer, mapNode> map = new HashMap<Integer, mapNode>();
		File file=new File(filename);
		
        if(!file.exists()||file.isDirectory())
        	throw new FileNotFoundException();       	
        
        FileInputStream fis=new FileInputStream(file);
		Scanner reader=new Scanner(fis);
		
		reader.nextLine();
		
		while(reader.hasNext())
		//while(i < 1000)
		{
			//int i=reader.nextInt();
			//System.out.print(i);
			String str=reader.nextLine();
			String arry[]=str.split("\\s+");
			
			int xid = Integer.parseInt(arry[0]);
			int pm = Integer.parseInt(arry[1]);
			int kind = Integer.parseInt(arry[3]);
			String zhan = arry[2];
			
			mapNode mn = new mapNode(xid, pm, kind, zhan);
			
			//System.out.println(mp.zhan);			
			if(xid != id)
			{
				id = xid;
			}
			else 
			{			
				setBit(i, i - 1);
			}
			
			map.put(i, mn);				
			i++;
		}
		System.out.println(map.size());
		//int num = 0;
		/*for(i = 0; i < map.size(); i++)
		{
			for(j = 0; j < map.size(); j++)
			{
				if(map.get(j).xid == map.get(i).xid)
				{	
					//setBit(i, j); 
					if(j % 10000 == 0) 
						System.out.println("i="+i+" j="+j);
				}
				//num = num + i +j;
			}
		}
	}*/
	
	public void setBit(int row, int col)
	{
		int i = col/8%BIT_ARRAY_LENGTH;
		int j = col%8;
		//char c = (char *) bf;
		//printf("i: %d, j: %d\n", i, j);
		//printf("c[i] : %2x\n", c[i]);
		matrix[row][i] = (byte)((1 << j) | matrix[row][i]);
		//printf("c[i] : %2x\n", c[i]);
	}

	/*int getBit(int row, int col)
	{
		int i = row/8%BIT_ARRAY_LENGTH;
		int j = row%8;
		//char *c = (char *) bf;
		if ((matrix[row][i] & (1 << j)) == 0)
			return 0;
		else 
			return 1;
	}*/
	
	public void calculate()
	{
		int i, j, k;
		
		for(k = 0; k < 3000; k++)
		{
			for(i = 0; i < 3000; i++)
			{
				for(j = 0; j < 3000; j++)
				{
					if(matrixLine[i][k] && matrixLine[k][j])
						matrixLine[i][j] = true;
				}
			}
		}
	}
		
}

class mapNode{
	
	int pm, kind;
	String zhan;
	
	public mapNode(int p, int k, String z)
	{
		//xid = x;
		pm = p;
		kind =k;		
		zhan = z;
	}
		
}
