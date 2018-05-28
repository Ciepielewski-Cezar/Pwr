#include "SparseMatrix.h"

using namespace std;

//-------------STA£E------------------
#define DEFAULT_DYMENSIONS 3
#define DEFAULT_RANGE 3
#define DEFAULT_DEF_VALUE 0
#define DEFAULT_NAME "defName"

#define REAL_LENGTH 0
#define VALUE_ARRAY_LENGTH 5
#define VALUE_ARRAY_EXTEND 5





//-------KOSTRUKTORY I DESTRUKTORY-------------


//kostruktor parametrowy
SparseMatrix::SparseMatrix(int dym, int rngVal[], int defVal, string nam) {
	dymensions = dym;
	range = new int[dymensions];
	defaultValue = defVal;
	name = nam;

	fillRangeValues(range, dymensions, rngVal);

	valueArray = new SparseCell*[VALUE_ARRAY_LENGTH];
	arrayLength = VALUE_ARRAY_LENGTH;
	realLength = REAL_LENGTH;

	cout << "create: " << name << endl;
}


//kostruktor bez nazwy
SparseMatrix::SparseMatrix(int dym, int rngVal[], int defVal) {
	dymensions = dym;
	range = new int[dymensions];
	defaultValue = defVal;
	name = DEFAULT_NAME;

	fillRangeValues(range, dymensions, rngVal);

	valueArray = new SparseCell*[VALUE_ARRAY_LENGTH];
	arrayLength = VALUE_ARRAY_LENGTH;
	realLength = REAL_LENGTH;

	cout << "create: " << name << endl;
}


/*
//konstruktor domyœlny
SparseMatrix::SparseMatrix() {
dymensions = DEFAULT_DYMENSIONS;
range = new int[DEFAULT_DYMENSIONS];
name = DEFAULT_NAME;
defaultValue = DEFAULT_DEF_VALUE;

fill(range, dymensions, DEFAULT_RANGE);

valueArray = new SparseCell*[VALUE_ARRAY_LENGTH];
arrayLength = VALUE_ARRAY_LENGTH;
realLength = REAL_LENGTH;

cout << "create: " << name << endl;
}

*/



//kostruktor kopiuj¹cy
SparseMatrix::SparseMatrix(const SparseMatrix &other) {
	dymensions = other.dymensions;
	range = new int[dymensions];
	defaultValue = other.defaultValue;
	name = other.name + "_copy";

	for (int i = 0; i < dymensions; i++)
		range[i] = other.range[i];

	arrayLength = other.arrayLength;
	realLength = other.realLength;

	//SparseCell **valueArray;
	valueArray = new SparseCell*[arrayLength];

	for (int i = 0; i < realLength; i++)
		//*(valueArray[i]) = *(other.valueArray[i]);   //new SparseCell(other.valueArray[i]);
		valueArray[i] = new SparseCell(*other.valueArray[i]);

	//valueArray[i] = other.valueArray[i];

	cout << "create: " << name << endl;
}


//destruktor
SparseMatrix::~SparseMatrix() {
	delete[] valueArray;
	delete[] range;

	dymensions = 0;
	name = DEFAULT_NAME;
	defaultValue = DEFAULT_DEF_VALUE;
	arrayLength = VALUE_ARRAY_LENGTH;
	realLength = REAL_LENGTH;

	cout << "destroy: " << name << endl;
}

//-----------OPERATORY---------
SparseMatrix& SparseMatrix::operator=(const SparseMatrix &other) {
	dymensions = other.dymensions;
	range = new int[dymensions];
	defaultValue = other.defaultValue;
	name = other.name + "_copyO";

	for (int i = 0; i < dymensions; i++)
		range[i] = other.range[i];

	arrayLength = other.arrayLength;
	realLength = other.realLength;

	valueArray = new SparseCell*[arrayLength];

	for (int i = 0; i < realLength; i++)
		valueArray[i] = new SparseCell(*other.valueArray[i]);

	//valueArray[i] = other.valueArray[i];


	return *this;
}



//-----------FUNKCJE G£ÓWNE---------

//funkcja "dodaj¹ca" now¹ wartoœæ do matrycy
bool SparseMatrix::setValue(int newVal, int coord[]) {
	if (!checkRange(range, coord)) {
		return false;
	}


	if (isRare(coord) && (newVal == defaultValue)) {
		deleteValue(coord);


		//test
		//cout << "arrayLength: " << arrayLength << endl;
		//for (int i = 0; i < realLength; i++) {
		//	cout << valueArray[i]->value << " " << endl;
		//}
		//dotad

	}
	else if (isRare(coord) && (newVal != defaultValue)) {

		valueArray[getIndex(coord)]->value = newVal;

		//test
		//cout << "arrayLength: " << arrayLength << endl;
		//for (int i = 0; i < realLength; i++) {
		//	cout << valueArray[i]->value << " " << endl;
		//}
		//dotad
	}
	else if (!isRare(coord) && (newVal != defaultValue)) {
		addValue(newVal, coord);
	}
	else (!isRare(coord) && (newVal == defaultValue));


	//SparseCell(int val, int length, int coor[])
	return true;
}



//funkcja zmieniaj¹ca wartoœæ domyœln¹
void SparseMatrix::changeDefaultValue(int newDefault) {
	defaultValue = newDefault;

	for (int i = 0; i < realLength; i++) {
		int *coor;
		coor = new int[dymensions];

		for (int j = 0; j < dymensions; j++)
			coor[j] = valueArray[i]->coordinate[j];

		if (valueArray[i]->value == defaultValue) {
			deleteValue(coor);
		}

		delete[] coor;
	}
}



//zwraca wartoœæ komórki z podanych koordynatów
int SparseMatrix::getValueFromCoord(int coord[]) {
	bool isSame = true;

	for (int i = 0; i < realLength; i++) {
		isSame = true;

		for (int j = 0; j < dymensions; j++) {
			if (valueArray[i]->coordinate[j] != coord[j])
				isSame = false;
		} // for j

		if (isSame == true)
			return valueArray[i]->value;
	} // for i
	return defaultValue;
}


void SparseMatrix::copyArray(const SparseMatrix &other) {
	dymensions = other.dymensions;
	range = new int[dymensions];
	defaultValue = other.defaultValue;

	for (int i = 0; i < dymensions; i++)
		range[i] = other.range[i];

	arrayLength = other.arrayLength;
	realLength = other.realLength;

	delete[] valueArray;
	valueArray = new SparseCell*[arrayLength];

	for (int i = 0; i < realLength; i++)
		valueArray[i] = new SparseCell(*other.valueArray[i]);
	//*(valueArray[i]) = *(other.valueArray[i]);   //new SparseCell(other.valueArray[i]);

	cout << "skopiowano" << endl;
}

void SparseMatrix::print(int arr[]) {
	cout << "[";
	if (isRare(arr)) {
		int ind = getIndex(arr);

		for (int i = 0; i < dymensions; i++) {
			cout << arr[i];

			if (i != dymensions - 1)
				cout << ",";
		}

		cout << "]:" << valueArray[ind]->getValue() << "; ";
	}
	else {
		for (int i = 0; i < dymensions; i++) {
			cout << arr[i];

			if (i != dymensions - 1)
				cout << ",";
		}
		cout << "]:" << defaultValue << "; ";
	}

}


void SparseMatrix::printRec(int arr[], int n) {

	for (int i = 0; i < range[n]; i++) {

		arr[n] = i;
		if (n == dymensions - 1)
			print(arr);
		else
			printRec(arr, n + 1);
	}
}

string SparseMatrix::toString() {
	int *arr;
	arr = new int[dymensions];

	cout << "size: [";
	for (int i = 0; i < dymensions; i++) {
		arr[i] = range[i];
		cout << arr[i];

		if (i != dymensions - 1)
			cout << ",";
	}
	cout << "] values: ";

	printRec(arr, 0);
	cout << endl;
	delete[] arr;

	return "";
}


string SparseMatrix::matrixToString() {
	string matrix = name + " size: [";

	for (int i = 0; i < dymensions; i++) {
		std::string s = std::to_string(range[i]);
		matrix += s;
		if (i != dymensions - 1)
			matrix += ", ";
	}
	matrix += "]\n";
	return matrix;
}





//funkcja zmieniaj¹ca nazwê obiektu
void SparseMatrix::setName(string newName) {
	name = newName;
}



//funkcja zwracaj¹ca nazwê obiektu
string SparseMatrix::getName() {
	return name;
}



void SparseMatrix::defRec(int arr[], int n, int oldVal) {
	for (int i = 0; i < range[n]; i++) {
		arr[n] = i;

		if (n == dymensions - 1)
			if (!isRare(arr)) {
				addValue(oldVal, arr);
			}
		else
			printRec(arr, n + 1);
	}
}


void SparseMatrix::defSwap(int newDef) {
	int oldDef = defaultValue;

	int *arr;
	arr = new int[dymensions];

	for (int i = 0; i < dymensions; i++) {
		arr[i] = range[i];
	}

	defRec(arr, 0, oldDef);
	delete[] arr;

	changeDefaultValue(newDef);
	/*
		for (int i = 0; i < realLength; i++) {
		int *coor;
		coor = new int[dymensions];

		for (int j = 0; j < dymensions; j++)
			coor[j] = valueArray[i]->coordinate[j];

		if (valueArray[i]->value == defaultValue) {
			deleteValue(coor);
		}
		delete[] coor;
	}

	*/
}




//--------FUNKCJE POMOCNICZE---------

//fun przepisuj¹ca d³ugoœci wymiarów
void SparseMatrix::fillRangeValues(int *array, int length, int val[]) {
	for (int i = 0; i < length; i++)
		array[i] = val[i];
}


// funkcja uzupe³niaj¹ca  d³ugoœci zerami
void SparseMatrix::fill(int *array, int length, int defRange) {
	for (int i = 0; i < length; i++)
		array[i] = defRange;
}


bool SparseMatrix::checkRange(int *thisRange, int coord[]) {
	for (int i = 0; i < dymensions; i++) {
		if (thisRange[i] < coord[i]) return false;
	}
	return true;
}


//sprawdza czy podane koordynaty maj¹ ju¿ przypisan¹ wartoœæ rzadk¹ w valueArray
bool SparseMatrix::isRare(int coord[]) {
	bool isSame = true;

	for (int i = 0; i < realLength; i++) {
		isSame = true;

		for (int j = 0; j < dymensions; j++) {
			if (valueArray[i]->coordinate[j] != coord[j])
				isSame = false;
		} // for (int j = 0; j < dymensions; j++)

		if (isSame == true) {
			//test
			//cout << "true" << endl;
			return true;
		} //(isSame == true)
	} // for (int i = 0; i < realLength; i++)

	//test
	//cout << "false" << endl;
	return false;
}


//zwraca indeks obiektu w valueArray o podanych koordynatach
int SparseMatrix::getIndex(int coord[]) {
	bool isSame = true;

	for (int i = 0; i < realLength; i++) {
		isSame = true;

		for (int j = 0; j < dymensions; j++) {
			if (valueArray[i]->coordinate[j] != coord[j])
				isSame = false;
		} // for j

		if (isSame == true)
			return i;
	} // for i
	return -1;
}


//dodaje now¹ komórkê do valueArray
void SparseMatrix::addValue(int newValue, int coord[]) {
	if (realLength == arrayLength) {
		extendCapity();	//powiêkszamy wielkoœæ tablicy jeœli jest zape³niona
	}
	valueArray[realLength] = new SparseCell(newValue, dymensions, coord);
	realLength++;
	/*
	//test
	//cout << "arrayLength: " << arrayLength << endl;
	for (int i = 0; i < realLength; i++) {
		cout << valueArray[i]->value << " " << endl;
	}
	*/
}


//powiêksza d³ugoœæ valueArrray o VALUE_ARRAY_EXTEND(domyœlnie 5)
void SparseMatrix::extendCapity() {
	SparseCell **newValueArray;
	newValueArray = new SparseCell*[arrayLength + VALUE_ARRAY_EXTEND];

	for (int i = 0; i < arrayLength; i++) {
		newValueArray[i] = valueArray[i];
	}

	arrayLength += VALUE_ARRAY_EXTEND;
	//Tutaj powinno nastapic zwolnienie tego na co wskazuje valueArray "delete valueArray;"
	delete[] valueArray;

	valueArray = newValueArray;
	//Tutaj powinno zostac zmienione to aby newValueArray juz nie wskazywal na to co teraz wskazuje valueArray. "newValueArray=nullptr"
	newValueArray = nullptr;
	//delete[] newValueArray;


}


//usuwa podan¹ komórkê
void SparseMatrix::deleteValue(int coord[]) {
	int delCell = getIndex(coord);
	//test
	//cout << "delCell: " << delCell << endl;

	delete valueArray[delCell]; //usuwa obiekt na tym wskaŸniku

	SparseCell **newValueArray;
	newValueArray = new SparseCell*[arrayLength - 1];

	if (delCell != 0) {

		for (int i = 0; i < delCell; i++) {
			newValueArray[i] = valueArray[i];
		}

		for (int i = delCell; i < realLength - 1; i++) {
			newValueArray[i] = valueArray[i + 1];
		}
	}
	else {
		for (int i = 1; i < realLength; i++) {
			newValueArray[i - 1] = valueArray[i];
		}

	}

	realLength -= 1;
	delete[] valueArray;

	valueArray = newValueArray;
	newValueArray = nullptr;
}


int SparseMatrix::getDymensions() {
	return dymensions;
}


int SparseMatrix::getRangeAt(int ind) {
	if (ind < 0 || ind >= dymensions) {
		return -1;
	}
	else {
		for (int i = 0; i < dymensions; i++) {
			if (i == ind)
				return range[i];
		}
	}
	return -1;
}











