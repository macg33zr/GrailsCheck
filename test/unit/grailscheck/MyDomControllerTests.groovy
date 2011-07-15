package grailscheck



import org.junit.*
import grails.test.mixin.*


@TestFor(MyDomController)
@Mock(MyDom)
class MyDomControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/myDom/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.myDomInstanceList.size() == 0
        assert model.myDomInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.myDomInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.myDomInstance != null
        assert view == '/myDom/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/myDom/show/1'
        assert controller.flash.message != null
        assert MyDom.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/myDom/list'


        def myDom = new MyDom()

        // TODO: populate domain properties

        assert myDom.save() != null

        params.id = myDom.id

        def model = controller.show()

        assert model.myDomInstance == myDom
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/myDom/list'


        def myDom = new MyDom()

        // TODO: populate valid domain properties

        assert myDom.save() != null

        params.id = myDom.id

        def model = controller.edit()

        assert model.myDomInstance == myDom
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/myDom/list'

        response.reset()


        def myDom = new MyDom()

        // TODO: populate valid domain properties

        assert myDom.save() != null

        // test invalid parameters in update
        params.id = myDom.id

        controller.update()

        assert view == "/myDom/edit"
        assert model.myDomInstance != null

        myDom.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/myDom/show/$myDom.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/myDom/list'

        response.reset()

        def myDom = new MyDom()

        // TODO: populate valid domain properties
        assert myDom.save() != null
        assert MyDom.count() == 1

        params.id = myDom.id

        controller.delete()

        assert MyDom.count() == 0
        assert MyDom.get(myDom.id) == null
        assert response.redirectedUrl == '/myDom/list'


    }


}