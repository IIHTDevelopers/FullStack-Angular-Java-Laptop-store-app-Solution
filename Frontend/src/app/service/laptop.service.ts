import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Laptop } from '../model/laptop.model';

@Injectable({
    providedIn: 'root'
})
export class LaptopService {
    private apiUrl = 'http://localhost:8081/laptopstore/laptops';

    constructor(private http: HttpClient) { }

    getAllLaptops(): Observable<Laptop[]> {
        return this.http.get<Laptop[]>(this.apiUrl);
    }

    getLaptopById(id: number): Observable<Laptop> {
        return this.http.get<Laptop>(`${this.apiUrl}/${id}`);
    }

    createLaptop(laptop: Laptop): Observable<Laptop> {
        return this.http.post<Laptop>(this.apiUrl, laptop);
    }

    updateLaptop(id: number, laptop: Laptop): Observable<Laptop> {
        return this.http.put<Laptop>(`${this.apiUrl}/${id}`, laptop);
    }

    deleteLaptop(id: number): Observable<void> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.delete<void>(url);
    }

    searchLaptopsByName(name: string): Observable<Laptop[]> {
        return this.http.get<Laptop[]>(`${this.apiUrl}/search?name=${name}`);
    }

    searchLaptopsByPrice(price: number): Observable<Laptop[]> {
        return this.http.get<Laptop[]>(`${this.apiUrl}/search?price=${price}`);
    }

    searchLaptopsByBrand(brand: string): Observable<Laptop[]> {
        return this.http.get<Laptop[]>(`${this.apiUrl}/search?brand=${brand}`);
    }
}
