import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CalculaCombinacoes {
	private FileWriter fstream;
	private BufferedWriter out;
	private int num_comb = 0;
	private int endCol = 0;
	private final int endLine = 485000;
	private Long totalComb;
	private int _end;
	private int _start;
	private int _n;
	private String _pathToSave;

	public CalculaCombinacoes(int start, int end, int n, String pathToSave){
		_start = start;
		_end = end;
		_n = n;
		String oldChar = "\\";
		String newChar = "//";
		_pathToSave = pathToSave.replace(oldChar.charAt(0),newChar.charAt(0));
	}
	
	public void calcula(){
		getNumberOfCombinations();
		endCol = totalComb.intValue() / endLine;
		if(endCol > 16000) endCol = 16000;
		if(endCol == 0) endCol = 1;
		
		//System.out.println("totalComb: " + totalComb);
		
		try {
			
			File file = new File(_pathToSave + "/gerador_combinacoes_resultado.csv");
			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
           
			fstream = new FileWriter(file.getAbsoluteFile());
			out = new BufferedWriter(fstream);

			getCombinations("",_start, _end, _n, 0);
			
			out.close();
			//System.out.println("Acabou!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private void getCombinations(String comb, int start, int end, int n, int size){
		String result = "";
		
		if(size < n){
			size++;
			for(int i = start; i<=end; i++){
				result = comb + "  " + i;  
				getCombinations(result,i + 1, end, n, size);
			}
		}
		if(comb.split("  ").length - 1 == n) {
			num_comb++;
			
			escreve(comb + ";");
			
		}
	}
	
	
	
	private void escreve(String output){
		
		try {
			if(num_comb % endCol == 0)
				out.write(output + "\n");
			else
				out.write(output);
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	private long p(long n){
		if(n==1)
			return 1;
		else
			return n * p(n-1);
	}
	
	private long pk(long n, long k){
		if(n==k)
			return 1;
		else
			return n * pk(n-1,k);
	}
	
	public long getNumberOfCombinations(){
		long n2 = _end - _start + 1;
		long n1 = n2 - _n;
		totalComb = pk(n2,n1)/p(_n);
		
		
		return totalComb;
	}
	
	
}
