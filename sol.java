package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class main_misc {
	
	
	public static final int MAX = 1_000_000;
	
	
	
	
	public static void main(String[] args) {
		
		
		List<Integer> m0 = new ArrayList<>(Arrays.asList(65,100));
		List<Integer> m1 = new ArrayList<>(Arrays.asList(70,150));
		List<Integer> m2 = new ArrayList<>(Arrays.asList(56,90));
		List<Integer> m3 = new ArrayList<>(Arrays.asList(75,190));
		List<Integer> m4 = new ArrayList<>(Arrays.asList(60,95));
		List<Integer> m5 = new ArrayList<>(Arrays.asList(68,110));
		
		List<List<Integer>> metric = new ArrayList<>(Arrays.asList(m0,m1,m2,m3,m4,m5));
		
		System.out.println(biggest_tower(metric));
		
		
	}
	
	private static void swap(List<List<Integer>> vec,int a,int b){
		
		List<Integer> tmp = vec.get(a);
		vec.set(a, vec.get(b));
		vec.set(b, tmp);
		
	}
	
	private static int partition(List<List<Integer>> vec,int strt,int end,int x) {
		
		int pivot = vec.get((strt+end)/2).get(x);
		
		while(strt <= end){
			
			while(vec.get(strt).get(x) < pivot){
				strt++;
			}
			
			while(vec.get(end).get(x) > pivot){
				end--;
			}
			if(strt <= end){
				swap(vec,strt,end);
				strt++;
				end--;
			}
		}
		
		return strt;
	}
		
	private static void quick_sort(List<List<Integer>> vec,int strt,int end,int x){
		
		int part = partition(vec,strt,end,x);
		
		if(strt < part-1) {
			quick_sort(vec,strt,part-1,x);
		}
		if(end > part) {
			quick_sort(vec,part,end,x);
		}
	}
	
	private static int biggest_tower(List<List<Integer>> metrics){
		
		//greedy algorithms aproach
		
		List<List<Integer>> cmp = new ArrayList<>();
		
		for(int i = 0;i < metrics.size();i++){
			List<Integer> metric =  metrics.get(i);
			cmp.add(new ArrayList<>(Arrays.asList(metric.get(0),metric.get(1),0)));
		}
		
		//sort based on the idx 0
		quick_sort(cmp,0,cmp.size()-1,0);
		
		for(int i = 0;i < cmp.size();i++) {
			int val = cmp.get(i).get(2);
			cmp.get(i).set(2, val+i);
		}
		
		//sort based on the idx 1
		quick_sort(cmp,0,cmp.size()-1,1);
		
		for(int i = 0;i < cmp.size();i++) {
			int val = cmp.get(i).get(2);
			cmp.get(i).set(2, val+i);
		}
		
		//sort based on the idx 2 that and find the best "path"
		quick_sort(cmp,0,cmp.size()-1,2);
		
		List<List<Integer>> path = new ArrayList<>();
		
		List<Integer> curr = new ArrayList<>(Arrays.asList(-1,-1));
		for(int i = 0;i < cmp.size();i++){
			
			List<Integer> tmp = cmp.get(i); 
			
			if(tmp.get(0) > curr.get(0) && tmp.get(1) > curr.get(1)) {
				path.add(tmp);
			}
			
			curr = path.get(i);
			
		}
		
		System.out.println(path);
		return path.size();
	}
	

}
