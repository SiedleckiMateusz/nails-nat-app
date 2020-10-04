package pl.siedleckimateusz.nailsnatapp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.siedleckimateusz.nailsnatapp.entity.UserEntity;
import pl.siedleckimateusz.nailsnatapp.repository.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SimpleUrlAuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepo userRepo;

    public SimpleUrlAuthenticationSuccessHandlerImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private static final Logger logger = LoggerFactory.getLogger(SimpleUrlAuthenticationSuccessHandlerImpl.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , Authentication authentication) throws IOException, ServletException {
        logger.info("I am in onAuthenticationSuccess method");


        super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
//        RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(requestURI);
//        requestDispatcher.include(httpServletRequest,httpServletResponse);

    }
}
