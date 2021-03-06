/**

   Copyright 2010 OpenEngSB Division, Vienna University of Technology

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.openengsb.config.dao.jpa;

import java.util.List;

import javax.persistence.NoResultException;

import org.openengsb.config.dao.PersistedObjectDao;
import org.openengsb.config.domain.PersistedObject;
import org.openengsb.config.domain.ServiceAssembly;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JpaPersistedObjectDao extends JpaBaseDao<PersistedObject> implements PersistedObjectDao {
    @Override
    @SuppressWarnings("unchecked")
    public List<PersistedObject> findByServiceAssembly(ServiceAssembly sa) {
        return em.createQuery("select p from PersistedObject p where p.serviceAssembly = :sa order by name")
                .setParameter("sa", sa).getResultList();
    }

    @Override
    public PersistedObject findByName(String name) {
        try {
            return (PersistedObject) em.createQuery("select p from PersistedObject p where p.name = :name")
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public PersistedObject findByName(ServiceAssembly sa, String name) {
        try {
            return (PersistedObject) em.createQuery(
                    "select p from PersistedObject p where p.name = :name and p.serviceAssembly = :sa").setParameter(
                    "name", name).setParameter("sa", sa).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
