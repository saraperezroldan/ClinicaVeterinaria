import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionCitasVeterinarioComponent } from './gestion-citas-veterinario.component';

describe('GestionCitasVeterinarioComponent', () => {
  let component: GestionCitasVeterinarioComponent;
  let fixture: ComponentFixture<GestionCitasVeterinarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GestionCitasVeterinarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestionCitasVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
