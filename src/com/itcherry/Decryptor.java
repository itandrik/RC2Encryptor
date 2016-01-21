/**
 * Дочірній клас, який забезпечує функцію дешифрування
 * заданого слова
 * 
 * @author Andrey Chernysh
 * @version 1.0 17/12/2015
 */
package com.itcherry;

import java.util.ArrayList;
import java.util.Arrays;

public class Decryptor extends Crypt{ //Клас Розшифровувач, що наслідує клас Крипт

	private StringBuilder decryptedWord;
	
	{
		decryptedWord = new StringBuilder();
	}
	public Decryptor(String key) {
		super(key);
	}
	private short[] mix(short[] block64) {
		short[] result = block64;

		for (int k = 3; k >= 0; k--) {
			// result[k] = (short)(result[k] >>> s[k]);
			result[k] = (short) ((result[k] - K[J] - (result[(k + 1) % 4] & result[(k + 2) % 4])
					- (~result[(k + 1) % 4] & result[(k + 3) % 4])) % Math.pow(2, 16));
			J--;
		}
		return result;
	}

	private short[] mesh(short[] block64) {
		short[] result = block64;
		byte j = 0;

		for (int i = 3; i >= 0; i--)
			result[i] = (short) ((result[i] - K[result[(i + 1) % 4] & 63]) % Math.pow(2, 16));
		return result;
	}

	private short[] getShortArrayFromString(String word){
		if(word.isEmpty()) return null;
		boolean []flag = new boolean[2];
		ArrayList<Short> al = new ArrayList<Short>();
		int k = 0;
		for(int i = 0; i < word.length();i++){
			flag[0] = false;
			flag[1] = false;
			for(int j = 0;j < 8;j++){
				if(word.charAt(i) == Crypt.stringCryptPositive[j]){
					flag[0] = true;
					break;
				}
				if(word.charAt(i) == Crypt.stringCryptNegative[j]){
					flag[1] = true;
					break;
				}
			}
			if(flag[0]){
				al.add(Short.parseShort(word.substring(k, i)));
				k = i + 1;
			}else if(flag[1]){
				al.add((short)((-1) * Short.parseShort(word.substring(k, i))));
				k = i + 1;
			}
		}
		short[] ret = new short[al.size()];
		for(int i  = 0;i < al.size();i++){
			ret[i] = al.get(i);
		}
		return ret;
		
	}
	public void decrypt(String wordEncrypted) {
		short[] wordShortEncrypted = getShortArrayFromString(wordEncrypted);
		wordShortDecrypted = new short[wordShortEncrypted.length];
		short[] block64 = new short[64];
		for (int i = 0; i < wordShortEncrypted.length; i += 4) {
			J = 63;
			int iter = 0;
			for (int k = i; k < i + 4; k++) {
				block64[iter++] = wordShortEncrypted[k];
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
				wordShortDecrypted[k] = block64[iter++];
				decryptedWord.append((char) wordShortDecrypted[k]);
			}
		}
	}
	public String getDecryptedWord(){
		return decryptedWord.toString();
	}
	void getInfo() {					//Системна функція для ведення логів
		System.out.println("Decryptor : ");
		System.out.print("Decrypted word in bytes : " + Arrays.toString(wordShortDecrypted) + "\nDecrypted word : "
				+ decryptedWord);
		System.out.println("\n");
	}
}
