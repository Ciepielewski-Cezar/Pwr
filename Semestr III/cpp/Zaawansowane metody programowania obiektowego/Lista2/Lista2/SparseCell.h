#pragma once
#include <iostream>
#include <string.h>
#include <cstring>
#include <string>

using namespace std;

//w tej klasie maj¹ byæ przechowywane wartoœci ró¿ne od
//standardowej 1-wymiarowej tablicy obiektów klasy SparseCell
class SparseCell{
private:
	int value;
	int coordinateLength;
	int *coordinate;


public:
	friend class SparseMatrix;


	//-------KOSTRUKTORY I DESTRUKTORY-------------
	SparseCell(int val, int length, int tab[]);
	SparseCell(int val, int length);
	SparseCell(const SparseCell &other);

	~SparseCell();


	//-----------OPERATORY---------
	SparseCell& operator=(const SparseCell &other);


	//--------FUNKCJE POMOCNICZE---------
	int getValue();
	void setValue(int newValue);
	void printCorrdinate();
	void fill(int *array, int length);
	void fillCoordinate(int length, int coor[]);

	void printCell();




};
