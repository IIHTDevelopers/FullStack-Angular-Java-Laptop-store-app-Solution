import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LaptopService } from './laptop.service';
import { Laptop } from '../model/laptop.model';

describe('LaptopService', () => {
    let service: LaptopService;
    let httpTestingController: HttpTestingController;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports: [HttpClientTestingModule],
        });
        service = TestBed.inject(LaptopService);
        httpTestingController = TestBed.inject(HttpTestingController);
    });

    afterEach(() => {
        httpTestingController.verify();
    });

    describe('business', () => {
        it('should be created', () => {
            expect(service).toBeTruthy();
        });

        it('should retrieve all laptops from the API', () => {
            const mockLaptops: Laptop[] = [
                { id: 1, name: 'Laptop 1', price: 1000, brand: 'Brand X', storage: '256GB', ram: '8GB', processor: 'i5' },
                { id: 2, name: 'Laptop 2', price: 1500, brand: 'Brand Y', storage: '512GB', ram: '16GB', processor: 'i7' },
            ];

            service.getAllLaptops().subscribe((laptops: any) => {
                expect(laptops).toEqual(mockLaptops);
            });

            const req = httpTestingController.expectOne('http://localhost:8081/laptopstore/laptops');
            expect(req.request.method).toEqual('GET');
            req.flush(mockLaptops);
        });

        it('should create a new laptop via the API', () => {
            const newLaptop: Laptop = {
                name: 'New Laptop',
                price: 2000,
                brand: 'Brand Z',
                storage: '512GB',
                ram: '16GB',
                processor: 'i7',
                id: 0
            };

            service.createLaptop(newLaptop).subscribe((createdLaptop: any) => {
                expect(createdLaptop).toEqual({ ...newLaptop, id: 1 }); // Assuming the server returns the new laptop with an ID (e.g., 1)
            });

            const req = httpTestingController.expectOne('http://localhost:8081/laptopstore/laptops');
            expect(req.request.method).toEqual('POST');
            req.flush({ ...newLaptop, id: 1 });
        });

        it('should search laptops by name via the API', () => {
            const searchKeyword = 'Laptop 1';
            const mockLaptops: Laptop[] = [
                { id: 1, name: 'Laptop 1', price: 1000, brand: 'Brand X', storage: '256GB', ram: '8GB', processor: 'i5' },
            ];

            service.searchLaptopsByName(searchKeyword).subscribe((laptops: any) => {
                expect(laptops).toEqual(mockLaptops);
            });

            const req = httpTestingController.expectOne(`http://localhost:8081/laptopstore/laptops/search?name=${searchKeyword}`);
            expect(req.request.method).toEqual('GET');
            req.flush(mockLaptops);
        });

        it('should search laptops by price via the API', () => {
            const searchPrice = 1000;
            const mockLaptops: Laptop[] = [
                { id: 1, name: 'Laptop 1', price: 1000, brand: 'Brand X', storage: '256GB', ram: '8GB', processor: 'i5' },
            ];

            service.searchLaptopsByPrice(searchPrice).subscribe((laptops: any) => {
                expect(laptops).toEqual(mockLaptops);
            });

            const req = httpTestingController.expectOne(`http://localhost:8081/laptopstore/laptops/search?price=${searchPrice}`);
            expect(req.request.method).toEqual('GET');
            req.flush(mockLaptops);
        });

        it('should search laptops by brand via the API', () => {
            const searchBrand = 'Brand X';
            const mockLaptops: Laptop[] = [
                { id: 1, name: 'Laptop 1', price: 1000, brand: 'Brand X', storage: '256GB', ram: '8GB', processor: 'i5' },
            ];

            service.searchLaptopsByBrand(searchBrand).subscribe((laptops: any) => {
                expect(laptops).toEqual(mockLaptops);
            });

            const req = httpTestingController.expectOne(`http://localhost:8081/laptopstore/laptops/search?brand=${searchBrand}`);
            expect(req.request.method).toEqual('GET');
            req.flush(mockLaptops);
        });

        it('should update a laptop via the API', () => {
            const updatedLaptop: Laptop = {
                id: 1,
                name: 'Updated Laptop',
                price: 1800,
                brand: 'Brand Y',
                storage: '512GB',
                ram: '16GB',
                processor: 'i7',
            };

            service.updateLaptop(updatedLaptop.id, updatedLaptop).subscribe((result: any) => {
                expect(result).toEqual(updatedLaptop);
            });

            const req = httpTestingController.expectOne(`http://localhost:8081/laptopstore/laptops/${updatedLaptop.id}`);
            expect(req.request.method).toEqual('PUT');
            req.flush(updatedLaptop);
        });

        it('should delete a laptop via the API', () => {
            const laptopId = 1;

            service.deleteLaptop(laptopId).subscribe((result: any) => {
                expect(result).toBeUndefined(); // Expect no data returned for successful deletion
            });

            const req = httpTestingController.expectOne(`http://localhost:8081/laptopstore/laptops/${laptopId}`);
            expect(req.request.method).toEqual('DELETE');
            req.flush(null, { status: 204, statusText: 'No Content' });
        });

        it('should handle errors during laptop update', () => {
            const updatedLaptop: Laptop = {
                id: 1,
                name: 'Updated Laptop',
                price: 1800,
                brand: 'Brand Y',
                storage: '512GB',
                ram: '16GB',
                processor: 'i7',
            };

            service.updateLaptop(updatedLaptop.id, updatedLaptop).subscribe(
                () => fail('Update should have failed with an error'),
                (error: any) => {
                    expect(error.status).toBe(404); // Assuming a 404 status code for not found
                }
            );

            const req = httpTestingController.expectOne(`http://localhost:8081/laptopstore/laptops/${updatedLaptop.id}`);
            expect(req.request.method).toEqual('PUT');
            req.flush('Laptop not found', { status: 404, statusText: 'Not Found' });
        });

        it('should handle errors during laptop deletion', () => {
            const laptopId = 1;

            service.deleteLaptop(laptopId).subscribe(
                () => fail('Deletion should have failed with an error'),
                (error: any) => {
                    expect(error.status).toBe(404); // Assuming a 404 status code for not found
                }
            );

            const req = httpTestingController.expectOne(`http://localhost:8081/laptopstore/laptops/${laptopId}`);
            expect(req.request.method).toEqual('DELETE');
            req.flush('Laptop not found', { status: 404, statusText: 'Not Found' });
        });
    });
});
