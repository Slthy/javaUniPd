public class ArrayUtil{
	public static double[] resize(double[] oldArray, int newLength){
		double[] newArray = new double[newLength];
		if (newLength < oldArray.length){
			for (int i = 0; i < newLength; i++) newArray[i] = oldArray[i];
			return newArray;
		}
		for(int i = 0; i < oldArray.length; i++) newArray[i] = oldArray[i];
		return newArray;
	}
	public static int[] resize(int[] oldArray, int newLength){
		int[] newArray = new int[newLength];
		if (newLength < oldArray.length){
			for (int i = 0; i < newLength; i++) newArray[i] = oldArray[i];
			return newArray;
		}
		for(int i = 0; i < oldArray.length; i++) newArray[i] = oldArray[i];
		return newArray;
	}
}
