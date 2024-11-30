import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionTratamientosComponent } from './gestion-tratamientos.component';

describe('GestionTratamientosComponent', () => {
  let component: GestionTratamientosComponent;
  let fixture: ComponentFixture<GestionTratamientosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GestionTratamientosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestionTratamientosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
