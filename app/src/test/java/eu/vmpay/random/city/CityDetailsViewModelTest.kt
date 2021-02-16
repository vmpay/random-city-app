package eu.vmpay.random.city

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import eu.vmpay.random.city.model.CityModel
import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.tools.ERROR_MESSAGE
import eu.vmpay.random.city.viewmodel.CityDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyLong
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class CityDetailsViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val repository = mock(Repository::class.java)

    private lateinit var viewModel: CityDetailsViewModel

    @Before
    fun setUp() {
        viewModel = CityDetailsViewModel(repository)
    }

    @Test
    fun happyTest() = testCoroutineRule.runBlockingTest {
        val cityModel = mock(CityModel::class.java)
        `when`(repository.getCityByUid(anyLong())).thenReturn(cityModel)

        viewModel.getCityById(0L)

        verify(repository).getCityByUid(anyLong())
        assertEquals(cityModel, viewModel.ldCityDetails.value)
        assertNull(viewModel.isError.value)
    }

    @Test
    fun emptyTest() = testCoroutineRule.runBlockingTest {
        `when`(repository.getCityByUid(anyLong())).thenReturn(null)

        viewModel.getCityById(0L)

        verify(repository).getCityByUid(anyLong())
        assertEquals(ERROR_MESSAGE, viewModel.isError.value?.peekContent())
        assertNull(viewModel.ldCityDetails.value)
    }

    @Test
    fun failTest() = testCoroutineRule.runBlockingTest {
        val exception = mock(Throwable::class.java)
        `when`(repository.getCityByUid(anyLong())).thenAnswer { throw exception }

        viewModel.getCityById(0L)

        verify(repository).getCityByUid(anyLong())
        assertEquals(ERROR_MESSAGE, viewModel.isError.value?.peekContent())
        assertNull(viewModel.ldCityDetails.value)
    }
}
