#pragma once
#include "SparseCell.h"

#include <iostream>
#include <string.h>
#include <cstring>
#include <string>




using namespace std;



//Wartoœci komórek, które s¹ ró¿ne od standardowych maj¹ byæ
//przechowywane w jednowymiarowej tablicy obiektów klasy SparseCell
class SparseMatrix{
private:
	int dymensions;	//ma byæ dowolna iloœæ
	int *range; //ró¿ne dla ka¿dego wymiaru
	int defaultValue; //wartoœæ domyœlna dla matrycy
	string name; //nazwa obiektu
	

	SparseCell **valueArray; //przechowuje wskaŸniki obiektów rzadkich
	int arrayLength; //d³ugoœæ tablicy
	int realLength; //zajêta iloœæ miejsc

public:
	//-------KOSTRUKTORY I DESTRUKTORY-------------

	SparseMatrix(int dym, int rngVal[], int defVal, string nam);
	SparseMatrix(int dym, int rngVal[], int defVal);
	//SparseMatrix();

	SparseMatrix(const SparseMatrix &other);

	~SparseMatrix();

	//-----------OPERATORY---------
	SparseMatrix& operator=(const SparseMatrix &other);


	//-----------FUNKCJE G£ÓWNE---------
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

