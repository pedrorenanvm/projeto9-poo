    package br.edu.ufersa.projeto9poo.models.repositories;

    import br.edu.ufersa.projeto9poo.models.entities.Cliente;
    import br.edu.ufersa.projeto9poo.models.utils.JPAUtil;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.EntityTransaction;

    import java.util.List;
    import java.util.Optional;

    public class ClienteRepositoryImpl implements ClienteRepository {

        private final EntityManager em = JPAUtil.pegarEntityManagerFactory();


        @Override
        public void cadastrar(Cliente cliente) {
            EntityTransaction tx = em.getTransaction();

            try{
                tx.begin();
                em.persist(cliente);
                tx.commit();
            }catch (RuntimeException e){
                if(tx.isActive()){
                    tx.rollback();
                }
                throw new RuntimeException("Erro ao cadastrar cliente", e);
            }
        }

        @Override
        public void editar(Cliente cliente) {
                EntityTransaction tx = em.getTransaction();

            try{
                tx.begin();
                em.merge(cliente);
                tx.commit();

            } catch (RuntimeException e){
                if(tx.isActive()){
                    tx.rollback();
                }
                throw new RuntimeException("Erro ao editar cliente", e);
            }
        }

        @Override
        public void deletar(Cliente cliente) {
            EntityTransaction tx = em.getTransaction();

            try{
                tx.begin();

                Cliente c = em.find(Cliente.class, cliente.getId());

                if(c != null){
                    em.remove(c);
                }
                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) {
                    tx.rollback();
                }
                throw new RuntimeException("Erro ao deletar cliente", e);
            }
        }

        @Override
        public Optional<Cliente> buscarPorId(Long id) {
            try{
                return Optional.ofNullable(em.find(Cliente.class, id));
            } catch (RuntimeException e) {
                return Optional.empty();
            }
        }

        @Override
        public List<Cliente> buscarTodos() {

            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();

        }
    }
