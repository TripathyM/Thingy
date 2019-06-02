- In Junit5 in order if we simply added mocks without such annotations like @RunWith(MockitoJUnitRunner.class), the mocks were not initialized and still null.
- To fix the above issue, followed this link https://stackoverflow.com/questions/40961057/how-to-use-mockito-with-junit5
- We can use `@ExtendWith(MockitoExtension.class)`. We used `@MockitoSettings` which does the same internally. Check out the documentation for more info.

- [ ] Difference between Junit 4 @Runwith and JUnit 5 @MockitoSettings
