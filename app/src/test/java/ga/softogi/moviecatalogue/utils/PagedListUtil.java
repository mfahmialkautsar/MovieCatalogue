package ga.softogi.moviecatalogue.utils;

import androidx.paging.PagedList;

import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagedListUtil {

    public static <T> PagedList<T> mockPagedList(List<T> list) {
        //noinspection unchecked
        PagedList<T> pagedList = mock(PagedList.class);
        Answer<T> answer = invocation -> {
            Integer index = invocation.getArgument(0);
            return list.get(index);
        };

        when(pagedList.get(anyInt())).thenAnswer(answer);
        when(pagedList.size()).thenReturn(list.size());

        return pagedList;
    }
}
