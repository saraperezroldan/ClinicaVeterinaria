import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionCitasAdministradorComponent } from './gestion-citas-administrador.component';

describe('GestionCitasAdministradorComponent', () => {
  let component: GestionCitasAdministradorComponent;
  let fixture: ComponentFixture<GestionCitasAdministradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GestionCitasAdministradorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestionCitasAdministradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
