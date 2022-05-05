package com.example.geektrust;

import java.util.List;

public interface IStockCommand {
	List<String> execute(String fullCommand);
}
