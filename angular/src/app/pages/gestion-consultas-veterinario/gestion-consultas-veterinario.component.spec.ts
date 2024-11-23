import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionConsultasVeterinarioComponent } from './gestion-consultas-veterinario.component';

describe('GestionConsultasVeterinarioComponent', () => {
  let component: GestionConsultasVeterinarioComponent;
  let fixture: ComponentFixture<GestionConsultasVeterinarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GestionConsultasVeterinarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestionConsultasVeterinarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
