import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {

    Map<String, Integer> sheetMap = new HashMap<>();
    public Spreadsheet(int rows) {
        for(int i = 1; i <= rows; i++){
            for(char c = 'A'; c <= 'Z'; c++){
                sheetMap.put(c+(String.valueOf(i)), 0);
            }
        }
    }
    
    public void setCell(String cell, int value) {
        sheetMap.put(cell, value);
    }
    
    public void resetCell(String cell) {
        sheetMap.put(cell, 0);
    }
    
    public int getValue(String formula) {
        int x = 0, y = 0;
        String[] operands = formula.split("\\+");
        operands[0] = operands[0].replace("=", "");
        if(operands[0].matches(".*[A-Z].*")){
            x = sheetMap.get(operands[0]);
        }else{
            x = Integer.parseInt(operands[0]);
        }

        if(operands[1].matches(".*[A-Z].*")){
            y = sheetMap.get(operands[1]);
        }else{
            y = Integer.parseInt(operands[1]);
        }

        return x + y;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */  

