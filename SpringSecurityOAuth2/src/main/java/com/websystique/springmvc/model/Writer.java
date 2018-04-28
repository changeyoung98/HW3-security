package com.websystique.springmvc.model;

import java.io.*;
import java.util.*;

import org.springframework.util.ResourceUtils;

public class Writer 
{
	private final String filename;
	private final int N;
	private final int num;
	private final String type;
	
	public String getFileName() {
		return filename;
	}
	public int getN() {
		return N;
	}
	public int getNum() {
		return num;
	}
	public String getType() {
		return type;
	}
	public static Vector<String> fileManage(String filename,Vector<String> vec) {
		try {
		File file = ResourceUtils.getFile("classpath:File/"+filename);
		InputStream in =new BufferedInputStream(new FileInputStream(file));
		InputStreamReader ir=new InputStreamReader(in,"utf-8");
	    BufferedReader reader = new BufferedReader(ir);
	    
	        String tempString = null;
	        while ((tempString = reader.readLine()) != null) {
	        	String[]s =tempString.split(" ");
	        	for(String temp:s) {
	        		vec.addElement(temp);
	        	}
	        }
	        reader.close();
	       
		}catch(IOException e) {
			System.out.print( "No such file.Please enter again: ");
		};
		
	    return vec;
	}
	
	public static int getValueN(int length,String Num) {
		
		int N =0;
		while (true) {    //check whether the input N is valid
			try {
				N=Integer.parseInt(Num);
				break;
			}
			catch(NumberFormatException e){
				System.out.println("Invalid N format. Please enter again. ");
				Scanner inputNum =new Scanner(System.in);
				Num=inputNum.nextLine();
			}
		

		}
		while ((N >= length) || (N <= 1)) {   //check whether the input N is valid
			System.out.println("Invalid N. Please enter again: ");
			Scanner inputNum =new Scanner(System.in);
			Num=inputNum.nextLine();
			N=Integer.parseInt(Num);
		}
		return N;
	}
	
	public static String buildKeyCol(int length,int i,int n,Vector<String> vec) {
		String key= new String();
		for (int j = 0; j < n; j++) {
			if (i + j < length) {
				key = key + vec.get(i + j)+" ";
			}
			else {
				key = key + vec.get(i + j - length)+" ";
			}
		}
		return key;
		
	}
	public static void buildCollection(Map<String, Vector<String>> col,String key,String val) {
		Vector<String> ve=new Vector<String>();
		if(col.containsKey(key)) {
		    ve=col.get(key);
		}
		ve.addElement(val);
		col.put(key, ve);
	}
	
	
	public static String newCurrent(String current) {
		int pos = current.indexOf(" ");
		//	sz = current.length();
			current = current.substring(pos + 1); 
			return current;
	}
	
	public static int getNumOfWords(String number) {
		int num;
		while (true) {    //check whether the input N is valid
			try {
				num=Integer.parseInt(number);
				break;
			}
			catch(NumberFormatException e){
				System.out.println("Invalid N format. Please enter again. ");
				Scanner inputNum =new Scanner(System.in);
				number=inputNum.nextLine();
			}
		

		}
		   //check whether the input num is valid
		return num;
			
	}
	
	public String getResult() {
		Map<String, Vector<String>> col=new HashMap<String, Vector<String>>();
		Vector<String> vec=new Vector<String>();
		
	//	System.out.print ( "Input file? ");
	//	@SuppressWarnings("resource")
	//	Scanner input=new Scanner(System.in);
	//	filename=input.nextLine();
		
	    fileManage(filename,vec);
	    int length = vec.size();
	
	 //   String Num= new String();
	//	System.out.println ("Value of N? ");
	//	Scanner inputN=new Scanner(System.in);
	//	Num=inputN.nextLine();
	    
//	    int N=getValueN(length,Num);
	    int n = N - 1;
		Vector<String> key_col= new Vector<String>();
		for (int i = 0; i < length; i++) {
			String key= new String();
			key=buildKeyCol(length,i,n,vec);
			key_col.addElement(key);
			if (i + n < length) {
				String val=new String();
				val=vec.get(i+n);
				buildCollection(col,key,val);
			}
			else {
				String val=new String();
				val=vec.get(i+n-length);
				buildCollection(col,key,val);

				
			}
		}    
		 //build a collection of all the N-1 words and their suffixes
		
	//	while (true) {
			
	//		String number;
		//	System.out.println("#of random words to generate(0 to quit)? ");
	//		Scanner inputNumber =new Scanner(System.in);
	//		number=inputNumber.nextLine();
	//		int num=getNumOfWords(number);
			
		/*		if (num == 0) {
					System.out.println( "Exciting." );
					return ;
				}   //0 to quit function
				if (num < N) {
					System.out.println( "Must be at least " + N + " words." );
					continue;
				}   //check the input num*/
				
				int x = col.size() - 1;
				int i = 0;
				String ch= new String();
				String result=new String();
			//	System.out.println ( "What do you want?(w for whole sentence, p for partial sentence.) ");
			//	Scanner inputChoice=new Scanner(System.in);
			//	ch=inputChoice.nextLine();
				ch=type;
				
				if (ch.equals("w")) {
					String current= new String();
					Random random = new Random();
					int ran_num=Math.abs(random.nextInt())%x;
				   //produce a random word
					while (current.length() == 0) {
						String curr = key_col.get(ran_num);
						if (curr.charAt(0) >= 'A' && curr.charAt(0) <= 'Z') {
							current = curr;
						}                        
						else {
							random = new Random();
							ran_num=Math.abs(random.nextInt())%x;
						}
					}

					System.out.print ("alalal");
					System.out.print (current);
					result=current;

					int k = 0;
					int sz = current.length();
					//check whether the ending is the ending of a sentence
					while ((k <= num - n) || (current.charAt(sz-2) != '?'&& current.charAt(sz-2) != '.'&& current.charAt(sz-2) != '!'/*&& current[sz - 2] != ','*/)) {
						int y = col.get(current).size();
						random = new Random();
						int ran=Math.abs(random.nextInt())%y;    //to choose the suffix randomly
					//	System.out.print ( col.get(current).get(ran) + " ");
						result+=col.get(current).get(ran) + " ";
						current = current + col.get(current).get(ran) + " ";
						current=newCurrent(current); // change the current prefix
						sz = current.length();
						k++;
					}
					//System.out.println( '\n' );
					result+='\n';
				}
				else if(ch.equals("p")){
					String current= new String();
					Random random = new Random();
					int ran_num=Math.abs(random.nextInt())%x;   //produce a random word
					current = key_col.elementAt(ran_num);
				//	System.out.print("..."+ current);
					result+="..."+current;
					int k = 0;
					int sz = current.length();
					//check whether the ending is the ending of a sentence
					while ((k <= num - n) ) {
						int y = col.get(current).size();
					    random = new Random();
						int ran=Math.abs(random.nextInt())%y;   //to choose the suffix randomly
					//	System.out.print ( col.get(current).get(ran) + " ");
						result+=col.get(current).get(ran) + " ";
						current = current + col.get(current).get(ran) + " ";
					
						current=newCurrent(current); // change the current prefix
						sz = current.length();
						
						k++;
					}
				//	System.out.println( "..."+'\n');
					result+="..."+'\n';
				}
				else {
					System.out.println("Invalid function." );
				}
				
				return result;
			//}
	}
	public Writer(String filename, Integer N, Integer num, String type) {
		this.filename=filename;
		this.N=N;
		this.num=num;
		this.type=type;
	}
}