import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialCitasVeterinarioComponent } from './historial-citas-veterinario.component';

describe('HistorialCitasVeterinarioComponent', () => {
  let component: HistorialCitasVeterinarioComponent;
  let fixture: ComponentFixture<HistorialCitasVeterinarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HistorialCitasVeterinarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HistorialCitasVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
