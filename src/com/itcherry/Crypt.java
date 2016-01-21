/**
 * ���������� ����, ���� �� ����-��������� ��� ����������,
 * ������������ � ����� �����, ���� ������ ���������� �����.
 * 
 * @author Andrey Chernysh
 * @version 1.0 17/12/2015
 */
package com.itcherry;

public class Crypt {						//���������� ����  
	protected String key;					//����
	protected short[] wordShortDecrypted;	//���� � ������ ������ ����� ���� short
	protected byte[] keyByte;				//���� � ������ ������ ����� ���� byte
	protected byte J;						//��������
	protected final byte[] s = { 1, 2, 3, 5 };
	protected short[] K = new short[64];
	/*��������������� ������� ��� ���������� �����*/
	private final short[] p = { 234, 210, 198, 98, 202, 125, 80, 226, 117, 250, 156, 224, 13, 34, 125, 164, 195, 141,
			191, 47, 100, 160, 194, 248, 195, 223, 104, 194, 229, 30, 139, 116, 158, 164, 61, 226, 37, 49, 188, 54, 195,
			204, 244, 0, 83, 38, 218, 4, 189, 213, 184, 130, 6, 211, 169, 19, 206, 104, 49, 48, 62, 60, 218, 40, 154,
			54, 50, 137, 142, 197, 80, 11, 83, 114, 89, 154, 126, 200, 2, 117, 36, 79, 61, 44, 126, 183, 156, 4, 221,
			98, 220, 96, 25, 54, 75, 212, 25, 221, 112, 7, 48, 204, 103, 189, 94, 231, 236, 84, 107, 11, 233, 107, 240,
			169, 177, 183, 47, 99, 203, 195, 89, 185, 61, 248, 196, 247, 215, 73, 222, 2, 248, 229, 125, 27, 121, 126,
			94, 88, 85, 45, 2, 108, 153, 104, 101, 82, 228, 56, 26, 146, 146, 193, 87, 26, 21, 16, 149, 40, 203, 116, 8,
			76, 197, 187, 46, 94, 137, 111, 49, 26, 219, 132, 130, 65, 247, 233, 131, 63, 127, 41, 124, 205, 135, 168,
			84, 138, 201, 227, 212, 48, 36, 240, 37, 255, 216, 173, 121, 219, 212, 6, 119, 218, 187, 101, 8, 84, 190,
			153, 185, 240, 168, 134, 166, 224, 200, 99, 63, 63, 85, 174, 231, 116, 45, 214, 133, 168, 118, 35, 145, 137,
			191, 176, 60, 25, 193, 13, 94, 193, 195, 197, 221, 181, 236, 33, 26, 127, 143, 88, 184, 177, 137, 226, 191,
			208, 97, 0 };
	static final char[] stringCryptPositive = {'f','A','m','R','q','G','j','K'};//����������� ����� �� ������ ��������� �� ����������� �������
	static final char[] stringCryptNegative = {'Z','v','L','x','C','w','o','Y'};//����������� ����� �� ������ ��������� �� ������� �������
	
	Crypt(String key){	//�����������
		this.key = key;
		keyByte = key.getBytes();
		setExtendedKey();
	}
	private void setExtendedKey() { //��������� ���������� �����
		short t = (byte) keyByte.length;
		short t1 = (short) (keyByte.length * 8);
		short t8 = (short) ((t1 + 7) / 8);
		short tm = (short) (255 % Math.pow(2, 8 + t1 - 8 * t8));
		short[] l = new short[127];

		for (int i = 0; i < t; i++)
			l[i] = keyByte[i];

		for (int i = t; i < 127; i++)
			l[i] = p[(l[i - 1] + l[i - t]) % 256];

		l[128 - t8] = p[l[128 - t8] & tm - 1];

		for (int i = 127 - t8; i >= 0; i--)
			l[i] = p[l[i + 1 - 1] ^ l[i + t8 - 1]];

		for (int i = 0; i < 63; i++)
			K[i] = (short) (l[2 * i] + 256 * l[2 * i + 1]);
	}
}