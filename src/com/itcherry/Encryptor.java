/**
 * Дочірній клас, який забезпечує функцію шифрування
 * заданого слова
 * 
 * @author Andrey Chernysh
 * @version 1.0 17/12/2015
 */
package com.itcherry;

import java.util.Arrays;

public class Encryptor extends Crypt{ //Клас енкриптор, що наслідує клас Крипт

	private short[] wordShortEncrypted;
	private StringBuilder encryptedWord = new StringBuilder();//Зашифроване слово

	Encryptor(String key) {
		super(key);
	}

	private short[] mix(short[] block64) {
		short[] result = block64;
		for (int k = 0; k < 4; k++) {
			result[k] = (short) ((result[k] + K[J] + (result[(k + 1) % 4] & result[(k + 2) % 4])
					+ (~result[(k + 1) % 4] & result[(k + 3) % 4])) % Math.pow(2, 16));
			J++;
			// result[k] = (short)(result[k] << s[k]);

		}
		return result;
	}

	private short[] mesh(short[] block64) {
		short[] result = block64;
		for (int i = 0; i < 4; i++)
			result[i] = (short) ((result[i] + K[result[(i + 1) % 4] & 63]) % Math.pow(2, 16));
		return result;
	}

	public void encrypt(String word) {
		byte[] wordByte = word.getBytes();
		short [] block64 = new short [64];
		byte[]temp;
		if(wordByte.length%4 != 0){
			temp = new byte[wordByte.length+(4-wordByte.length%4)];
			temp = Arrays.copyOf(wordByte, temp.length);
			for(int i = wordByte.length;i < temp.length;i++)
				temp[i] = 32;
		}
		else temp = wordByte;
		wordShortEncrypted = new short [temp.length]; 
		for (int i = 0; i < temp.length; i += 4) {
			int iter = 0;
			J = 0;
			for (int k = i; k < i + 4; k++) {
				block64[iter++] = (short) temp[k];
			}
			for (int k = 0; k < 5; k++)
				block64 = mix(block64);
			block64 = mesh(block64);
			for (int k = 0; k < 6; k++)
				block64 = mix(block64);
			block64 = mesh(block64);
			for (int k = 0; k < 5; k++) {
				block64 = mix(block64);
			}
			iter = 0;
			for (int k = i; k < i + 4; k++) {
				wordShortEncrypted[k] = block64[iter];
				encryptedWord.append(Math.abs(wordShortEncrypted[k]));
				encryptedWord.append(wordShortEncrypted[k] > 0? stringCryptPositive[iter%8] : stringCryptNegative[iter%8]);
				iter++;
			}
		}
		J = 0;
	}

	void getInfo() {		//Системна функція для ведення логів
		System.out.println("Ecryptor");
		System.out.println("Key : " + key);
		System.out.println("Key in bytes : " + Arrays.toString(keyByte));
		System.out.print("Encrypted word in bytes : " + Arrays.toString(wordShortEncrypted) + "\nEncrypted word : "
				+ encryptedWord);
		System.out.println("\n");
	}

	public String getEncryptedWord(){
		return encryptedWord.toString();
	}
	public short[] getEncryptedWordShort() {
		return wordShortEncrypted;
	}

}