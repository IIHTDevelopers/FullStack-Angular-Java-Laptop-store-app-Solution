import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LaptopService } from '../service/laptop.service';
import { Laptop } from '../model/laptop.model';

@Component({
    selector: 'app-laptop',
    templateUrl: './laptop.component.html',
    styleUrls: ['./laptop.component.css'],
})
export class LaptopComponent implements OnInit {
    laptops: Laptop[] = [];
    filteredLaptops: Laptop[] = [];
    laptopForm!: FormGroup;
    searchForm!: FormGroup;
    formSubmitted = false;

    constructor(private laptopService: LaptopService, private formBuilder: FormBuilder) {
        this.laptopForm = this.formBuilder.group({
            name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
            price: ['', [Validators.required, Validators.min(0), Validators.max(9999)]],
            brand: ['', [Validators.required]],
            storage: ['', [Validators.required]],
            ram: ['', [Validators.required]],
            processor: ['', [Validators.required]],
        });

        this.searchForm = this.formBuilder.group({
            searchName: [''],
            searchPrice: [''],
            searchBrand: [''],
        });
    }

    ngOnInit(): void {
        this.getAllLaptops();
    }

    getAllLaptops(): void {
        this.laptopService.getAllLaptops().subscribe((laptops) => {
            this.laptops = laptops;
            this.filteredLaptops = laptops; // Initialize the filteredLaptops with all laptops initially
        });
    }

    onSearch(): void {
        const searchName = this.laptopForm.get('name')?.value.toLowerCase();
        const searchPrice = this.laptopForm.get('price')?.value;
        const searchBrand = this.laptopForm.get('brand')?.value.toLowerCase();

        // Filter the laptops based on search criteria
        this.filteredLaptops = this.laptops.filter((laptop) => {
            return (
                laptop.name.toLowerCase().includes(searchName) &&
                (searchPrice === null || !isNaN(searchPrice)) && // Check for valid numeric value
                (searchPrice === null || laptop.price <= searchPrice) &&
                laptop.brand.toLowerCase().includes(searchBrand)
            );
        });
    }

    deleteLaptop(id: number): void {
        this.laptopService.deleteLaptop(id).subscribe((_) => {
            this.getAllLaptops();
        });
    }

    onSubmit(): void {
        this.formSubmitted = true;
        if (this.laptopForm.valid) {
            const newLaptop: Laptop = {
                name: this.laptopForm.get('name')?.value,
                price: this.laptopForm.get('price')?.value,
                brand: this.laptopForm.get('brand')?.value,
                storage: this.laptopForm.get('storage')?.value,
                ram: this.laptopForm.get('ram')?.value,
                processor: this.laptopForm.get('processor')?.value,
                id: 0,
            };

            this.laptopService.createLaptop(newLaptop).subscribe((_) => {
                this.getAllLaptops();
                this.laptopForm.reset(); // Reset the form after successful submission
                this.formSubmitted = false;
            });
        }
    }

    editLaptop(id: number): void {
        const laptopToEdit = this.laptops.find((laptop) => laptop.id === id);
        if (laptopToEdit) {
            this.laptopForm.patchValue({
                name: laptopToEdit.name,
                price: laptopToEdit.price,
                brand: laptopToEdit.brand,
                storage: laptopToEdit.storage,
                ram: laptopToEdit.ram,
                processor: laptopToEdit.processor,
            });
        }
    }

    resetForm(): void {
        this.laptopForm.reset();
    }
}
