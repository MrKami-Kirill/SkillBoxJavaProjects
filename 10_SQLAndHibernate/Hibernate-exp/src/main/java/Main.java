import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main
{
    public static void main(String[] args) {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<Course> query = builder.createQuery(Course.class);
//            Root<Course> root = query.from(Course.class);
//            query.select(root).where(builder.greaterThan(root.get("price"), 100000)).orderBy(builder.desc(root.get("price")));
//            List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();
//            courseList.forEach(System.out::println);
//            String hql = "From " + Course.class.getSimpleName() + " Where price >= 120000" + " Order by price DESC";
//            List<Course> courseList = session.createQuery(hql).setMaxResults(5).getResultList();
//            courseList.forEach(System.out::println);

            List<Subscription> subscriptions = session.createQuery("" +
                    " from Subscription sub" +
                    " join fetch sub.course c" +
                    " join fetch sub.student s" +
                    "", Subscription.class).getResultList();

            List<PurchaseList> LinkedPurchaseLists = session.createQuery(" from PurchaseList", PurchaseList.class).getResultList();

            subscriptions.forEach(s -> {
                LinkedPurchaseLists.forEach(p -> {
                    if (p.getStudentId() == null && s.getStudent().getName().equals(p.getId().getStudentName())) {
                        p.setStudentId(s.getStudent().getId());
                    }
                    if (p.getCourseId() == null && s.getCourse().getName().equals(p.getId().getCourseName())) {
                        p.setCourseId(s.getCourse().getId());
                    }
                });
            });

            LinkedPurchaseLists.forEach(System.out::println);
            //subscriptions.forEach(System.out::println);


            transaction.commit();
            sessionFactory.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
