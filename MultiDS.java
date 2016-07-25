package war;
import java.util.Random;

class MultiDS<T> implements PrimQ<T>, Reorder
{
	private T[] bag;
	private int capacity;
	private int numItems;

	MultiDS(int i) 
	{
		numItems=0;
		capacity = i;
		T[] tempBag = (T[]) new Object[capacity];
		bag = tempBag; 
	}

	public T[] toArray()
	{

		T[] result = (T[]) new Object [capacity];
		System.arraycopy(bag, 0, result, 0, numItems);
		return result;
	}

	@Override
	public boolean addItem(T newItem) 
	{
		boolean result = true;
		if(full())
		{
			result = false;
		}
		else
		{
			bag[numItems]= newItem;
			numItems++;    
		}
		return result;   
	}

	private int getIndexOf(T item)
	{
		int where = -1;

		boolean found = false;
		int index=0;
		while(!found && (index <numItems))
		{
			if(item.equals(bag[index]))
			{
				found = true;
				where=index;
			}
			index++;
		}
		return where;
	}


	@Override
	public T removeItem() 
	{   
		T result = null;
		if(!empty())
		{            
			result=bag[0];
			bag[0]=null;           
			shiftLeft();            
			numItems--;
		}      
		return result;
	}


	@Override
	public boolean full() 
	{
		return numItems==bag.length;
	}

	@Override
	public boolean empty() 
	{   
		return size() == 0;
	}

	@Override
	public int size() 
	{
		return numItems;
	}

	public int getFreq(T item)
	{
		int counter = 0;
		for(int i = 0; i < numItems; i++)
		{
			if (item.equals(bag[i]))
			{
				counter++;
			}
		}
		return counter;
	}

	public T getCard(int inum)
	{
		return bag[inum];
	}

	@Override
	public void clear() 
	{
		while(!empty())
		{
			removeItem();
		}
	}

	@Override
	public void reverse() 
	{
		for (int j = 0; j < numItems / 2; j++)
		{
			T temp = bag[j];
			bag[j] = bag[numItems- j - 1];
			bag[numItems - j - 1] = temp;
		}

	}

	@Override
	public void shiftRight() 
	{         
		T last = bag[numItems - 1];

		for(int i = numItems - 1; i > 0; i--) {
			bag[i] = bag[i - 1];
		}
		bag[0] = last;  
	}

	@Override
	public void shiftLeft() 
	{

		T first=bag[0];
		for (int i = 0; i < numItems-1; i++) {           
			bag[i] = bag[i+1];            
		}
		bag[numItems - 1]=first;      
	}

	private boolean contains(T item)
	{
		boolean wasFound = false;
		int i =0;
		while(!wasFound && (i < numItems))
		{
			if(item.equals(bag[i]))
			{
				wasFound=true;
			}
		}
		return wasFound;
	}

	@Override
	public void shuffle() 
	{
		Random rand = new Random();

		for (int i = numItems-1; i > 0; i--) 
		{
			int index = rand.nextInt(i+1);
			// Simple swap
			T a = bag[index];
			T b = bag[i];
			bag[index] = b;
			bag[i] = a;
		}
	}

	@Override
	public String toString() 
	{
		String str = " ";        
		for (int i = 0; i < numItems; i++)
		{

			str = str + bag[i];
			str = str + " ";      

		}
		return str;
	} 

	public void buildDeck(){
		MultiDS<Card> t=new MultiDS<Card>(52);
		for (int a = 0; a < Card.Suits.values().length; a++) {
			for (int b = 0; b < Card.Ranks.values().length; b++) {
				Card c1 = new Card(Card.Suits.values()[a], Card.Ranks.values()[b]);
				t.addItem(c1);
			}
		}
	}
}