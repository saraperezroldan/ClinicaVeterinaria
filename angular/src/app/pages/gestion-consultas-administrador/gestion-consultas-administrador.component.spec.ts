import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionConsultasAdministradorComponent } from './gestion-consultas-administrador.component';

describe('GestionConsultasAdministradorComponent', () => {
  let component: GestionConsultasAdministradorComponent;
  let fixture: ComponentFixture<GestionConsultasAdministradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GestionConsultasAdministradorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestionConsultasAdministradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
