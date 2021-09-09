package com.mockito.mockito;

public class SomeBusinessImpl {
	private DataService dataService;
	
	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}



	int findGreatest() {
		int[] data =dataService.retrieveAllData();
		int greatest = -1;
		for(int value : data) {
			greatest = Math.max(value, greatest);
		}
		return greatest;
	}
	
}

