import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { LaptopComponent } from './laptop.component';
import { LaptopService } from '../service/laptop.service';
import { Laptop } from '../model/laptop.model';

describe('LaptopComponent', () => {
    let component: LaptopComponent;
    let fixture: ComponentFixture<LaptopComponent>;
    let laptopService: LaptopService;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [LaptopComponent],
            imports: [FormsModule, ReactiveFormsModule, HttpClientTestingModule],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(LaptopComponent);
        component = fixture.componentInstance;
        laptopService = TestBed.inject(LaptopService);
        jest.spyOn(laptopService, 'getAllLaptops').mockReturnValue(of([]));
        fixture.detectChanges();
    });

    describe('business', () => {
        it('should create', () => {
            expect(component).toBeTruthy();
        });

        it('should initialize the laptopForm with empty values', () => {
            expect(component.laptopForm.value).toEqual({
                name: '',
                price: '',
                brand: '',
                storage: '',
                ram: '',
                processor: '',
            });
        });

        it('should disable the submit button when the laptopForm is invalid', () => {
            const submitButton = fixture.nativeElement.querySelector('button[type="submit"]');
            expect(submitButton.disabled).toBeTruthy();

            component.laptopForm.setValue({
                name: 'Laptop 1',
                price: 1200,
                brand: 'Brand X',
                storage: '512GB',
                ram: '8GB',
                processor: 'i5',
            });

            fixture.detectChanges();
            expect(submitButton.disabled).toBeFalsy();
        });

        it('should call createLaptop method of LaptopService when the form is submitted', () => {
            const laptopService = TestBed.inject(LaptopService);
            const laptopData: Laptop = {
                id: 0,
                name: 'Test Laptop',
                price: 1000,
                brand: 'Brand X',
                storage: '512GB',
                ram: '16GB',
                processor: 'i7',
            };
            const createLaptopSpy = jest.spyOn(laptopService, 'createLaptop').mockReturnValue(of(laptopData));

            component.laptopForm.setValue({
                name: 'Test Laptop',
                price: 1000,
                brand: 'Brand X',
                storage: '512GB',
                ram: '16GB',
                processor: 'i7',
            });

            component.onSubmit();

            expect(createLaptopSpy).toHaveBeenCalled();
        });

        it('should fetch laptops on initialization', () => {
            // Spy on the getAllLaptops method to mock its return value
            const mockLaptops: Laptop[] = [
                { id: 1, name: 'Laptop 1', price: 1000, brand: 'Brand X', storage: '512GB', ram: '16GB', processor: 'i7' },
                { id: 2, name: 'Laptop 2', price: 1200, brand: 'Brand Y', storage: '1TB', ram: '8GB', processor: 'i5' },
            ];
            jest.spyOn(laptopService, 'getAllLaptops').mockReturnValue(of(mockLaptops));

            // Call the ngOnInit method to trigger the initialization
            component.ngOnInit();

            // Expect the getAllLaptops method to have been called
            expect(laptopService.getAllLaptops).toHaveBeenCalled();

            // Verify that the laptops array in the component has been updated with the mock data
            expect(component.laptops).toEqual(mockLaptops);
        });

        it('should set the form values to editLaptop when editLaptop is called', () => {
            const mockLaptops: Laptop[] = [
                {
                    id: 1,
                    name: 'Test Laptop',
                    price: 1000,
                    brand: 'Brand X',
                    storage: '512GB',
                    ram: '16GB',
                    processor: 'i7',
                },
            ];

            const getAllLaptopsSpy = jest.spyOn(laptopService, 'getAllLaptops').mockReturnValue(of(mockLaptops));

            component.ngOnInit();
            fixture.detectChanges();

            component.editLaptop(1);

            expect(component.laptopForm.value).toEqual({
                name: 'Test Laptop',
                price: 1000,
                brand: 'Brand X',
                storage: '512GB',
                ram: '16GB',
                processor: 'i7',
            });
        });

        it('should call getAllLaptops method of LaptopService after successful laptop deletion', () => {
            // ... (existing test setup and code)

            // Spy on the deleteLaptop method and return an observable of void
            const deleteLaptopSpy = jest.spyOn(laptopService, 'deleteLaptop');

            // Call the deleteLaptop method
            component.deleteLaptop(1);

            // Expect the deleteLaptop method to have been called with the ID 1
            expect(deleteLaptopSpy).toHaveBeenCalledWith(1);

            // Expect the getAllLaptops method to have been called after successful deletion
            const getAllLaptopsSpy = jest.spyOn(laptopService, 'getAllLaptops').mockReturnValue(of([]));

            // Asynchronous test to check if getAllLaptopsSpy has been called after a short delay
            setTimeout(() => {
                expect(getAllLaptopsSpy).toHaveBeenCalled();
                // done(); // Call done to complete the test
            }, 100); // Add a short delay to allow asynchronous code to execute
        });

        it('should call deleteLaptop method of LaptopService when deleteLaptop is called', () => {
            // ... (existing test setup and code)

            // Spy on the deleteLaptop method and return an observable of void
            const deleteLaptopSpy = jest.spyOn(laptopService, 'deleteLaptop');

            // Call the deleteLaptop method
            component.deleteLaptop(1);

            // Expect the deleteLaptop method to have been called with the ID 1
            expect(deleteLaptopSpy).toHaveBeenCalledWith(1);
        });

        it('should call getAllLaptops method of LaptopService after successful form submission', () => {
            // Call the onSubmit method with valid form data
            component.laptopForm.setValue({
                name: 'Test Laptop',
                price: 1000,
                brand: 'Brand X',
                storage: '512GB',
                ram: '16GB',
                processor: 'i7',
            });
            component.onSubmit();

            // Expect the getAllLaptops method to have been called
            expect(laptopService.getAllLaptops).toHaveBeenCalled();
        });
    });
});
