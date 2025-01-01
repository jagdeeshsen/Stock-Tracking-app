package com.example.capxproject;

public class StockDataModel {

    String symbol,open,high,low,price,volume,latest_trading_day,previous_close,change,change_percent;

    public StockDataModel() {
    }

    public StockDataModel(String symbol, String open, String high, String low, String price, String volume, String latest_trading_day, String previous_close, String change, String change_percent) {
        this.symbol = symbol;
        this.open = open;
        this.high = high;
        this.low = low;
        this.price = price;
        this.volume = volume;
        this.latest_trading_day = latest_trading_day;
        this.previous_close = previous_close;
        this.change = change;
        this.change_percent = change_percent;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getLatest_trading_day() {
        return latest_trading_day;
    }

    public void setLatest_trading_day(String latest_trading_day) {
        this.latest_trading_day = latest_trading_day;
    }

    public String getPrevious_close() {
        return previous_close;
    }

    public void setPrevious_close(String previous_close) {
        this.previous_close = previous_close;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChange_percent() {
        return change_percent;
    }

    public void setChange_percent(String change_percent) {
        this.change_percent = change_percent;
    }
}
