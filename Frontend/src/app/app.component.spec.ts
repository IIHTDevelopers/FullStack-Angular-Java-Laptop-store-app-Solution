import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LaptopComponent } from './component/laptop.component';
import { ReactiveFormsModule } from '@angular/forms';

describe('AppComponent', () => {
    let fixture: ComponentFixture<AppComponent>;
    let app: AppComponent;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [AppComponent, LaptopComponent],
            imports: [HttpClientModule, ReactiveFormsModule]
        }).compileComponents();
        fixture = TestBed.createComponent(AppComponent);
        app = fixture.componentInstance;
    });

    describe('business', () => {
        it('should create the app', () => {
            const fixture = TestBed.createComponent(AppComponent);
            const app = fixture.componentInstance;
            expect(app).toBeTruthy();
        });

        it('should have "Laptop Store App" in <h1>', () => {
            // Trigger change detection to update the template with the initial data
            fixture.detectChanges();

            // Access the <h1> element using nativeElement
            const h1Element: HTMLElement = fixture.nativeElement.querySelector('h1');
            const expectedTitle = 'Laptop Store App';

            // Assert that the text content of the <h1> element matches the expected title
            expect(h1Element.textContent).toContain(expectedTitle);
        });
    });
});
