#pragma once
#include "SparseCell.h"

#include <iostream>
#include <string.h>
#include <cstring>
#include <string>




using namespace std;



//Warto�ci kom�rek, kt�re s� r�ne od standardowych maj� by�
//przechowywane w jednowymiarowej tablicy obiekt�w klasy SparseCell
class SparseMatrix{
private:
	int dymensions;	//ma by� dowolna ilo��
	int *range; //r�ne dla ka�dego wymiaru
	int defaultValue; //warto�� domy�lna dla matrycy
	string name; //nazwa obiektu
	

	SparseCell **valueArray; //przechowuje wska�niki obiekt�w rzadkich
	int arrayLength; //d�ugo�� tablicy
	int realLength; //zaj�ta ilo�� miejsc

public:
	//-------KOSTRUKTORY I DESTRUKTORY-------------

	SparseMatrix(int dym, int rngVal[], int defVal, string nam);
	SparseMatrix(int dym, int rngVal[], int defVal);
	//SparseMatrix();

	SparseMatrix(const SparseMatrix &other);

	~SparseMatrix();

	//-----------OPERATORY---------
	SparseMatrix& operator=(const SparseMatrix &other);


	//-----------FUNKCJE G��WNE---------
	bool setValue(int newVal, int coord[]);
	void copyArray(const SparseMatrix &other);

	void print(int arr[]);
	void printRec(int arr[], int n);
	string toString();
	string matrixToString();

	void defRec(int arr[], int n, int oldVal);
	void defSwap(int newDef);


	void changeDefaultValue(int newDefault);
	int getValueFromCoord(int coord[]);
	


	//--------FUNKCJE POMOCNICZE---------

	
	void fillRangeValues(int *array, int length, int val[]);
	void fill(int *array, int length, int defRange);
	bool checkRange(int *thisRange, int coord[]);
	bool isRare(int coord[]);
	int getIndex(int coord[]);

	void addValue(int newValue, int coord[]);
	void extendCapity();
	void deleteValue(int coord[]);


	void setName(string newName);
	string getName();
	int getDymensions();
	int getRangeAt(int ind);


};

