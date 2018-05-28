#include "SparseCell.h"

using namespace std;


//-------KOSTRUKTORY I DESTRUKTORY-------------


//kostruktor parametrowy
SparseCell::SparseCell(int val, int length, int coor[]) {
	value = val;
	coordinateLength = length;
	coordinate = new int[length];

	fillCoordinate( coordinateLength, coor);
}

//kostruktor parametrowy bez koordynatów
SparseCell::SparseCell(int val, int length) {
	value = val;
	coordinateLength = length;
	coordinate = new int[length];

	fill(coordinate, coordinateLength);
}

//kostruktor kopiuj¹cy
SparseCell::SparseCell(const SparseCell &other) {
	value = other.value;
	coordinateLength = other.coordinateLength;
	coordinate = new int[coordinateLength];

	for (int i = 0; i < coordinateLength; i++)
		coordinate[i] = other.coordinate[i];
}


//destruktor
SparseCell::~SparseCell() {
	value = 0;
	coordinateLength = -1;
	delete[] coordinate;
}

//-----------OPERATORY---------
SparseCell& SparseCell::operator=(const SparseCell &other) {
	value = other.value;
	coordinateLength = other.coordinateLength;
	coordinate = new int[coordinateLength];

	for (int i = 0; i < coordinateLength; i++)
		coordinate[i] = other.coordinate[i];	//czy tu nie przypisze na to samo

	return *this;
}


//--------FUNKCJE POMOCNICZE---------
int SparseCell::getValue() {
	return value;
}

void SparseCell::setValue(int newValue) {
	value = newValue;
}

void SparseCell::printCorrdinate() {
	for (int i = 0; i < coordinateLength; i++) {
		cout << coordinate[i] << " ";
	}
	cout << endl;
}

void SparseCell::fill(int *array, int length) {
	for (int i = 0; i < length; i++)
		array[i] = 0;
}

void SparseCell::fillCoordinate( int length, int coor[]) {
	for (int i = 0; i < length; i++)
		coordinate[i] = coor[i];
}

void SparseCell::printCell() {
	cout << "[";

	for (int i = 0; i < coordinateLength; i++) {
		cout << coordinate[i] << ",";
	}
	cout << "]:" << value << "; ";
}

