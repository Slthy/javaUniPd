//Borsato Alessandro

public class PizzaMap extends FixedArrayMap{
	private void resize(){
		Pair[] np = new Pair[super.p.length * 2];
		System.arraycopy(super.p, 0, np, 0, super.p.length);
		p = np;
	}
	public Object put(Object key, Object value){
		Object output = super.get(key);
		if (!(key instanceof String) || !(value instanceof Double)) throw new IllegalArgumentException();
		try {
			super.put(key, value);
		} catch (FullMapException e) {
			resize();
			super.put(key, value);
		}
		return output;
    }
    /*private static void merge(Pair a[], Pair[] b, Pair[] c){
    	int ia = 0, ib = 0, ic = 0;
    	while (ib < b.length && ic < c.length){
    		if ((double) b[ib].getValue() < (double) c[ic].getValue())
    			a[ia++] = b[ib++];
    		else 
    			a[ia++] = c[ic++];
    	}
    	while (ib < b.length) a[ia++] = b[ib++];
    	while (ic < c.length) a[ia++] = c[ic++];
    }
    //complessità: nlogn
    private static void mergePrint(Pair[] a){
    	if (a == null) return;
    	if (a.length < 2) return;
    	int mid = a.length / 2;
    	Pair[] left = new Pair[mid];
    	Pair[] right = new Pair[a.length - mid];
    	System.arraycopy(a, 0, left, 0, left.length);
    	System.arraycopy(a, mid, right, 0, right.length);

    	mergePrint(left);
    	mergePrint(right);

    	merge(a, left, right);
    }
    public String toString(){
    	Pair[] np = new Pair[super.size()];
    	System.arraycopy(super.p, 0, np, 0, super.size());
    	String output = "";
    	mergePrint(np);
    	for (int i = 0; i < super.size(); i++) {
    		output += String.format("%s\n", np[i].toString());
    	}
    	return output;
    }*/
    private static String merge(Pair[] b, Pair[] c){
    	int ia = 0, ib = 0, ic = 0;
    	String output = "";
    	while (ib < b.length && ic < c.length){
    		if ((double) b[ib].getValue() < (double) c[ic].getValue())
    			output += String.format("%s\n", b[ib++].toString());
    		else 
    			output += String.format("%s\n", c[ic++].toString());
    	}
    	while (ib < b.length) output += String.format("%s\n", b[ib++].toString());
    	while (ic < c.length) output += String.format("%s\n", c[ic++].toString());

    	return output;
    }
    //complessità: nlogn
    private static String mergePrint(Pair[] a){
    	if (a == null) return "";
    	if (a.length < 2) return "";
    	int mid = a.length / 2;
    	Pair[] left = new Pair[mid];
    	Pair[] right = new Pair[a.length - mid];
    	System.arraycopy(a, 0, left, 0, left.length);
    	System.arraycopy(a, mid, right, 0, right.length);

    	mergePrint(left);
    	mergePrint(right);

    	return merge(left, right);
    }
    public String toString(){
		Pair[] np = new Pair[super.size()];
    	System.arraycopy(super.p, 0, np, 0, super.size());
    	return mergePrint(np);
    }
}