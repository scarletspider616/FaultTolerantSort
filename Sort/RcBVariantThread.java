public interface RcBVariantThread {
	public int[] getResult();
	public void run() throws ThreadTimeoutException;
}