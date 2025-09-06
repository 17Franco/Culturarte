/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logica.Categoria.Categoria;
import logica.DTO.DTOPropuesta;
import logica.Colaboracion.Colaboracion;
import logica.Propuesta.Propuesta;
import logica.Usuario.Proponente;
import logica._enum.Estado;
import logica._enum.TipoRetorno;


public class ManejadorPropuesta {
    private static ManejadorPropuesta instancia = null;

    private ManejadorPropuesta() {}
    
    public static ManejadorPropuesta getinstance() {
        if (instancia == null)
            instancia = new ManejadorPropuesta();
        return instancia;
    }
    public void nuevaPropuesta(Propuesta p) {
        if (p == null) {
            return;
        }
        EntityManager em = PersistenciaManager.getEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        try {
            Proponente prop = em.find(Proponente.class, p.getProponente().getNickname());
            if (prop != null) {
                p.setProponente(prop);
                prop.getPropCreadas().put(p.getTitulo(), p);
            }
            
            em.persist(p);
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public boolean existeProp(String titulo) {
        EntityManager em = PersistenciaManager.getEntityManager();
        try {
            Propuesta prop = em.find(Propuesta.class, titulo);
            return prop != null;
        } finally {
            em.close();
        }
    } 
    public Propuesta buscarPropuestaPorTitulo(String titulo) {
        EntityManager em = PersistenciaManager.getEntityManager();
        try {
            return em.find(Propuesta.class, titulo);
        } finally {
            em.close();
        }
    }
    public List<Propuesta> listarPropuestas() {
        EntityManager em = PersistenciaManager.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Propuesta p", Propuesta.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    public Set<DTOPropuesta> obtenerPropuestas(String estadoInput) {
        EntityManager em = PersistenciaManager.getEntityManager();
        Set<DTOPropuesta> result = new HashSet<>();

        //"SELECT p FROM Propuesta p JOIN Registro_Estados re ON p.titulo = re.propuesta WHERE re.estado = :estadoInput AND re.fechaReg = (SELECT MAX(re2.fechaReg) FROM Registro_Estados re2 WHERE re2.propuesta = re.propuesta)";

        
        try {
            TypedQuery<Propuesta> q = em.createQuery("SELECT p FROM Propuesta p", Propuesta.class);
            List<Propuesta> propuestas = q.getResultList();
            for (Propuesta p : propuestas) {
                Estado ultimoEstado = null;
                if (!p.getHistorialEstados().isEmpty()) {
                    ultimoEstado = p.getHistorialEstados().get(0).getEstado();
                }
                if (!estadoInput.isEmpty() && (ultimoEstado == null || !ultimoEstado.name().equals(estadoInput))) {
                    continue; // saltar propuestas que no coincidan
                }
                DTOPropuesta dto = new DTOPropuesta();
                dto.extraerDatosPropuesta(p);
                result.add(dto);
            }

        } finally {
            em.close();
        }
        return result;
    }
    public int getMontoRecaudado(String titulo) {
        Propuesta p = buscarPropuestaPorTitulo(titulo);
        if (p == null) {
            return 0;
        }

        int recaudado = 0;
        for (Colaboracion c : p.getAporte()) {
            recaudado += c.getMonto();
        }
        return recaudado;
    }

    public List<String> listColaboradores(String titulo) {
        Propuesta p = buscarPropuestaPorTitulo(titulo);
        List<String> colab = new ArrayList<>();
        if (p != null) {
            for (Colaboracion c : p.getAporte()) {
                colab.add(c.getColaborador().getNickname());
            }
        }
        return colab;
    }
    public void actualizarEstado(String titulo) {
        EntityManager em = PersistenciaManager.getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            Propuesta p = em.find(Propuesta.class, titulo);
            if (p != null) {
                int montoRecibido = getMontoRecaudado(titulo);

                if (montoRecibido == 0) {
                    p.addEstHistorial(Estado.PUBLICADA);
                } else if (montoRecibido > 0 && montoRecibido != p.getMontoTotal()) {
                    p.addEstHistorial(Estado.EN_FINANCIACION);
                } else if (montoRecibido > 0 && montoRecibido == p.getMontoTotal()) {
                    p.addEstHistorial(Estado.FINANCIADA);
                }

                em.merge(p);
            }
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void eliminarColaboracion(String nick, String titulo) {
        EntityManager em = PersistenciaManager.getEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            Propuesta p = em.find(Propuesta.class, titulo);
            if (p != null) {
                Colaboracion aux = p.getAporte().stream()
                        .filter(c -> c.getColaborador().getNickname().equals(nick))
                        .findFirst()
                        .orElse(null);

                if (aux != null) {
                    p.getAporte().remove(aux);
                    em.remove(em.contains(aux) ? aux : em.merge(aux));
                }
            }
            t.commit();
        } catch (Exception e) {
            if (t.isActive()) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public Propuesta getPropuesta(String titulo) {
        EntityManager em = PersistenciaManager.getEntityManager();
        try {
            return em.find(Propuesta.class, titulo);  // busca por PK
        } finally {
            em.close();
        }
    }
    public void UpdatePropuesta(String titulo, String descripcion, String tipo, String rutaImagen,String lugar, LocalDate fechaEvento, int precio, int montoTotal,List<TipoRetorno> retorno, String categoria, String usuario, Estado estado) {
       
        EntityManager em = PersistenciaManager.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            
            Propuesta propuesta = em.find(Propuesta.class, titulo);

            if (propuesta != null) {
                propuesta.setDescripcion(descripcion);
                propuesta.setTipo(tipo);
                propuesta.setImagne(rutaImagen);
                propuesta.setLugar(lugar);
                propuesta.setFecha(fechaEvento);
                propuesta.setPrecio(precio);
                propuesta.setMontoTotal(montoTotal);
                propuesta.setRetornos(retorno);
                Categoria cat = em.find(Categoria.class, categoria);
                propuesta.setCategoria(cat);
                propuesta.addEstHistorial(estado);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
