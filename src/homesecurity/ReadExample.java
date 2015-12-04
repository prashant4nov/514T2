package homesecurity;


import java.io.*;

public class ReadExample
{
	public int readSound(String str) 
	{	
		double max = Double.MIN_VALUE;
		int maxCount=0;
		int cursor=0;
		try
		{
			// Open the wav file specified as the first argument
		
			WavFile wavFile = WavFile.openWavFile(new File(str));

			// Display information about the wav file
			wavFile.display();

			// Get the number of audio channels in the wav file
			int numChannels = wavFile.getNumChannels();

			// Create a buffer of 100 frames
			double[] buffer = new double[100 * numChannels];

			int framesRead;
			
			int count=0;
			int maxIndex=0;
			
			

			do
			{
				// Read frames into buffer
				framesRead = wavFile.readFrames(buffer, 100);

				// Loop through frames and look for minimum and maximum value
				for (int s=0 ; s<framesRead * numChannels ; s++)
				{
					if (Math.abs(buffer[s]) > max) 
					{
						max = Math.abs(buffer[s]);
						maxIndex=s;
					}
				}
				
				for(int k=0 ; k<framesRead * numChannels ; k++)
				{
					if(Math.abs(buffer[k])>max*0.8)
					{
						cursor=k;
						break;
					}
				}
				                    
				for(int i=cursor;i<framesRead*numChannels; i++)
				{
					if(Math.abs(buffer[i])<max*0.8)
					{
						count++;
					}
					if(Math.abs(buffer[i])>max*0.8)
					{
						if(count>maxCount)
						{
							maxCount=count;
						}
						count=0;
					}
				}
			}
			while (framesRead != 0);

			// Close the wavFile
			wavFile.close();

			// Output the minimum and maximum value
			System.out.printf("Max: %f\n", max);
			System.out.println("distance"+maxCount);
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		return identifyAlarm(maxCount, max);	
		
	}
	
	public int identifyAlarm(int maxDistance, double max)
	{
		if(maxDistance<=30000 && max>0.9999)
		{
			//window
			//return 1; 
			return AlarmStores.doorAlarm;
		}
		
		else if(30000<maxDistance && maxDistance<=70000 && max>0.99999)
		{
			//fire
			//return 2;
			return AlarmStores.smokeAlarm;
		}
		
		else if(100000<maxDistance && maxDistance<180000 && max>0.99999)
		{
			//gas
			//return 3;
			return AlarmStores.gasAlarm;
		}
		
		else
		{
			//return 4;
			return AlarmStores.noneAlarm;
		}
	}
	
//	public static void main(String[] args)
//	{
//		System.out.println("alarm:"+" "+readSound("smokeAlarmTemp.wav"));
//	}
}
