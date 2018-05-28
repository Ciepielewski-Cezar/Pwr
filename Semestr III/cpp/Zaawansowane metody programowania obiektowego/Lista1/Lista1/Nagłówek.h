#pragma once
#include <iostream>
#include <string.h>
#include <cstring>
#include <string>


void zmienDlugoscWektora(int &staraDlugosc, int nowaDlugosc, int *wartosci, int *offset, int *domyslna);
void przepiszTab(int *staraTablica, int staraDlugosc, int nowaDlugosc, int domyslna);
bool ustalWartDomysl(int *staraDomyslna, int nowaDomyslna, int dlugosc, int *wartosci);
void sortowanie(int *tablica, int dlugosc);
void zmienKomorke(int *wartosci, int *offset, int *domyslna, int ktoraKomorka, int nowaWartoscKomorki, int dlugosc);
void wczytajWartRzadka(int *wartosci, int *offset, int *domyslna, int ktoraKomorka, int &dlugosc);
void wydrukuj(int *wartosci, int *offset, int dlugosc, int *domyslna);
void usunDynamiczna(int *wartosci, int *offset, int &dlugoscWektora, int *wartoscDomyslna);
void usunWartosc(int *offset, int *wartosci, int &dlugosc, int ktoraKomorka);
void dodajWartosc(int *offset, int *wartosci, int &dlugosc, int ktoraKomorka, int wartoscKomorki);
//void powiekszWartOffset(int *wartosci, int *offset, int domyslna);
void wypelnijTablice(int *tablica, int nowaWart, int dlugosc);
void zainicjujWektor(int &dlugoscWektora, int *wartoscDomyslna, int *offsetTab, int *wartosciTab, int dlugosc, int domyslna);
void wyswietlTab(int *tablica, int dlugosc);
void ZmienXpoczatkowychWartosciNaY(int *wartosci, int *offset, int zmienna, int zakres, int *domyslna, int dlugosc);
void printHelp();