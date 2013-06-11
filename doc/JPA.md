# JPA
## Test PersistenceUnit
    <persistence-unit name="test" transaction-type="RESOURCE_LOCAL">
      <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
      <exclude-unlisted-classes>false</exclude-unlisted-classes>
      <properties>
        <property name="javax.persistence.jdbc.driver" value="org.apache.derby.jdbc.ClientDriver" />
        <property name="javax.persistence.jdbc.url" value="jdbc:derby://localhost:1527/test;create=true" />
        <property name="javax.persistence.jdbc.user" value="test" />
        <property name="javax.persistence.jdbc.password" value="test" />            
        <property name="eclipselink.ddl-generation" value="drop-and-create-tables"/>
        <property name="eclipselink.logging.level" value="FINEST" />
      </properties>
    </persistence-unit>
