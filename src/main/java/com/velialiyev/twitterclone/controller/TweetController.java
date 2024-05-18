package com.velialiyev.twitterclone.controller;

import com.velialiyev.twitterclone.dto.LikeRetweetBookmarkDto;
import com.velialiyev.twitterclone.dto.TweetDto;
import com.velialiyev.twitterclone.dto.TweetResponseDto;
import com.velialiyev.twitterclone.entity.BookmarkEntity;
import com.velialiyev.twitterclone.entity.EventEntity;
import com.velialiyev.twitterclone.entity.UserEntity;
import com.velialiyev.twitterclone.repository.BookmarkRepository;
import com.velialiyev.twitterclone.repository.UserRepository;
import com.velialiyev.twitterclone.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/posts")
@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;


    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createTweet(@RequestBody TweetDto tweetDto){
        this.tweetService.tweet(tweetDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTweet(@PathVariable(name = "id") Long id){
        this.tweetService.deleteTweet(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tweets-by-username/{username}")
    public ResponseEntity<List<TweetResponseDto>> getTweetsByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok( this.tweetService.getTweetsByUsername(username));
    }

    @GetMapping("/retweets-by-username/{username}")
    public ResponseEntity<List<TweetResponseDto>> getRetweetsByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok( this.tweetService.getRetweetsByUsername(username));
    }

    @GetMapping("/replies-by-username/{username}")
    public ResponseEntity<List<TweetResponseDto>> getRepliesByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok( this.tweetService.getRepliesByUsername(username));
    }

    @GetMapping("/liked-by-username/{username}")
    public ResponseEntity<List<TweetResponseDto>> getLikedByUsername(@PathVariable(name = "username") String username){
        return ResponseEntity.ok( this.tweetService.getLikedByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<TweetResponseDto>> getAll(){
        List<TweetResponseDto> tweets = this.tweetService.getAll();

        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByUsername(principal.getSubject()).orElseThrow();

        ArrayList<String> contentPreferences = new ArrayList<String>();

        if (Boolean.parseBoolean(user.getContentPreference1())){
            contentPreferences.add("musica");
            contentPreferences.add("melodía");
            contentPreferences.add("sonido");
            contentPreferences.add("ritmo");
            contentPreferences.add("armonía");
            contentPreferences.add("canción");
            contentPreferences.add("sonoridad");
            contentPreferences.add("acordes");
            contentPreferences.add("tonada");
            contentPreferences.add("melodioso");
            contentPreferences.add("compás");
            contentPreferences.add("tune");
            contentPreferences.add("pista");
            contentPreferences.add("sinfonía");
            contentPreferences.add("tempo");
            contentPreferences.add("beat");
            contentPreferences.add("concierto");
            contentPreferences.add("partitura");
            contentPreferences.add("interpretación");
            contentPreferences.add("instrumental");
            contentPreferences.add("alegre");
            contentPreferences.add("danza");
            contentPreferences.add("harmonioso");
            contentPreferences.add("melódico");
            contentPreferences.add("voz");
            contentPreferences.add("instrumento");
            contentPreferences.add("jazz");
            contentPreferences.add("rock");
            contentPreferences.add("pop");
            contentPreferences.add("clásico");
            contentPreferences.add("género musical");
            contentPreferences.add("estilo musical");
            contentPreferences.add("instrumentación");
            contentPreferences.add("composición");
            contentPreferences.add("artistas");
            contentPreferences.add("bandas");
            contentPreferences.add("canciones populares");
            contentPreferences.add("historia de la música");
            contentPreferences.add("eventos musicales");
            contentPreferences.add("tendencias musicales");
            contentPreferences.add("instrumentos musicales");
            contentPreferences.add("notas musicales");
            contentPreferences.add("letra");
            contentPreferences.add("improvisación");
            contentPreferences.add("grabación");
            contentPreferences.add("producción musical");
            contentPreferences.add("conciertos");
            contentPreferences.add("festivales de música");
            contentPreferences.add("clubes nocturnos");
            contentPreferences.add("pistas de baile");
            contentPreferences.add("álbum");
            contentPreferences.add("compositor");
            contentPreferences.add("productor musical");
            contentPreferences.add("DJ");
            contentPreferences.add("canción de éxito");
            contentPreferences.add("sintonía pegadiza");
            contentPreferences.add("instrumentos de cuerda");
            contentPreferences.add("instrumentos de viento");
            contentPreferences.add("notación musical");
            contentPreferences.add("riff");
            contentPreferences.add("arreglos");
            contentPreferences.add("banda sonora");
            contentPreferences.add("escala musical");
            contentPreferences.add("acústico");
            contentPreferences.add("electrónico");
            contentPreferences.add("batería");
            contentPreferences.add("bajo");
            contentPreferences.add("guitarra");
            contentPreferences.add("piano");
            contentPreferences.add("violín");
            contentPreferences.add("saxofón");
            contentPreferences.add("trompeta");
            contentPreferences.add("trombón");
            contentPreferences.add("flauta");
            contentPreferences.add("órgano");
            contentPreferences.add("acordeón");
            contentPreferences.add("vibrato");
            contentPreferences.add("falsete");
            contentPreferences.add("solfeo");
            contentPreferences.add("intervalo");
            contentPreferences.add("transposición");
            contentPreferences.add("consonancia");
            contentPreferences.add("disonancia");
            contentPreferences.add("crescendo");
            contentPreferences.add("decrescendo");
            contentPreferences.add("legato");
            contentPreferences.add("staccato");
            contentPreferences.add("arpegio");
            contentPreferences.add("contrapunto");
            contentPreferences.add("música de fondo");
            contentPreferences.add("música ambiental");
            contentPreferences.add("música clásica");
            contentPreferences.add("música folclórica");
            contentPreferences.add("música étnica");
            contentPreferences.add("música religiosa");
            contentPreferences.add("melodía");
            contentPreferences.add("sonido");
            contentPreferences.add("ritmo");
            contentPreferences.add("armonía");
            contentPreferences.add("canción");
            contentPreferences.add("sonoridad");
            contentPreferences.add("acordes");
            contentPreferences.add("tonada");
            contentPreferences.add("melodioso");
            contentPreferences.add("compás");
            contentPreferences.add("tune");
            contentPreferences.add("pista");
            contentPreferences.add("sinfonía");
            contentPreferences.add("tempo");
            contentPreferences.add("beat");
            contentPreferences.add("concierto");
            contentPreferences.add("partitura");
            contentPreferences.add("interpretación");
            contentPreferences.add("instrumental");
            contentPreferences.add("alegre");
            contentPreferences.add("danza");
            contentPreferences.add("harmonioso");
            contentPreferences.add("melódico");
            contentPreferences.add("voz");
            contentPreferences.add("instrumento");
            contentPreferences.add("jazz");
            contentPreferences.add("rock");
            contentPreferences.add("pop");
            contentPreferences.add("clásico");
            contentPreferences.add("género musical");
            contentPreferences.add("estilo musical");
            contentPreferences.add("instrumentación");
            contentPreferences.add("composición");
            contentPreferences.add("artistas");
            contentPreferences.add("bandas");
            contentPreferences.add("canciones populares");
            contentPreferences.add("historia de la música");
            contentPreferences.add("eventos musicales");
            contentPreferences.add("tendencias musicales");
            contentPreferences.add("instrumentos musicales");
            contentPreferences.add("notas musicales");
            contentPreferences.add("letra");
            contentPreferences.add("improvisación");
            contentPreferences.add("grabación");
            contentPreferences.add("producción musical");
            contentPreferences.add("conciertos");
            contentPreferences.add("festivales de música");
            contentPreferences.add("clubes nocturnos");
            contentPreferences.add("pistas de baile");
            contentPreferences.add("álbum");
            contentPreferences.add("compositor");
            contentPreferences.add("productor musical");
            contentPreferences.add("DJ");
            contentPreferences.add("canción de éxito");
            contentPreferences.add("sintonía pegadiza");
            contentPreferences.add("instrumentos de cuerda");
            contentPreferences.add("instrumentos de viento");




        }

        if (Boolean.parseBoolean(user.getContentPreference2())){
            contentPreferences.add("danza");
            contentPreferences.add("baile");
            contentPreferences.add("coreografía");
            contentPreferences.add("movimiento");
            contentPreferences.add("ritmo");
            contentPreferences.add("bailar");
            contentPreferences.add("pasos");
            contentPreferences.add("coreógrafo");
            contentPreferences.add("danza contemporánea");
            contentPreferences.add("bailarín");
            contentPreferences.add("baile folclórico");
            contentPreferences.add("bailarina");
            contentPreferences.add("ensayo");
            contentPreferences.add("escenario");
            contentPreferences.add("ballet");
            contentPreferences.add("paso");
            contentPreferences.add("interpretación");
            contentPreferences.add("ensayo general");
            contentPreferences.add("clase de baile");
            contentPreferences.add("coreografía moderna");
            contentPreferences.add("baile tradicional");
            contentPreferences.add("expresión corporal");
            contentPreferences.add("técnica de baile");
            contentPreferences.add("danza urbana");
            contentPreferences.add("danza contemporánea");
            contentPreferences.add("danza clásica");
            contentPreferences.add("movimiento fluido");
            contentPreferences.add("pasos elegantes");
            contentPreferences.add("coreografía dinámica");
            contentPreferences.add("improvisación");
            contentPreferences.add("baile de salón");
            contentPreferences.add("género de baile");
            contentPreferences.add("estilo de danza");
            contentPreferences.add("coreografía");
            contentPreferences.add("bailarines");
            contentPreferences.add("espectáculo de danza");
            contentPreferences.add("evento de danza");
            contentPreferences.add("teatro de danza");
            contentPreferences.add("movimiento corporal");
            contentPreferences.add("arte del movimiento");
            contentPreferences.add("pasión por la danza");
            contentPreferences.add("coreógrafo");
            contentPreferences.add("clases de danza");
            contentPreferences.add("baile de salón");
            contentPreferences.add("danza contemporánea");
            contentPreferences.add("ballet clásico");
            contentPreferences.add("danza moderna");
            contentPreferences.add("danza folclórica");
            contentPreferences.add("baile urbano");
            contentPreferences.add("performance");
            contentPreferences.add("expresión artística");
            contentPreferences.add("disciplina corporal");
            contentPreferences.add("cuerpo en movimiento");
            contentPreferences.add("movimiento sincronizado");
            contentPreferences.add("bailar en grupo");
            contentPreferences.add("entrenamiento de danza");
            contentPreferences.add("técnica de baile");
            contentPreferences.add("flexibilidad");
            contentPreferences.add("gracia");
            contentPreferences.add("elegancia");
            contentPreferences.add("coreografía dinámica");
            contentPreferences.add("bailar en grupo");
            contentPreferences.add("entrenamiento de danza");
            contentPreferences.add("flexibilidad");
            contentPreferences.add("gracia");
            contentPreferences.add("elegancia");
            contentPreferences.add("arte del movimiento");
            contentPreferences.add("baile flamenco");
            contentPreferences.add("bailar salsa");
            contentPreferences.add("danza contemporánea");
            contentPreferences.add("danza moderna");
            contentPreferences.add("danza folclórica");
            contentPreferences.add("baile urbano");
            contentPreferences.add("performance");
            contentPreferences.add("expresión artística");
            contentPreferences.add("disciplina corporal");
            contentPreferences.add("cuerpo en movimiento");
            contentPreferences.add("movimiento sincronizado");
            contentPreferences.add("danza tribal");
            contentPreferences.add("ballet contemporáneo");
            contentPreferences.add("baile africano");
            contentPreferences.add("danza del vientre");
            contentPreferences.add("danza oriental");
            contentPreferences.add("ritmos latinos");
            contentPreferences.add("tango");
            contentPreferences.add("samba");
            contentPreferences.add("jazz dance");
            contentPreferences.add("tap dance");
            contentPreferences.add("hip-hop dance");
            contentPreferences.add("breakdance");
            contentPreferences.add("krumping");
            contentPreferences.add("locking");
            contentPreferences.add("popping");
            contentPreferences.add("waacking");
            contentPreferences.add("danza acrobática");
            contentPreferences.add("danza aérea");
            contentPreferences.add("danza vertical");
            contentPreferences.add("danza en tela");
            contentPreferences.add("danza en aro");
            contentPreferences.add("contact improvisation");
            contentPreferences.add("danza butoh");
            contentPreferences.add("danza teatro");
            contentPreferences.add("danza expresionista");
            contentPreferences.add("danza postmoderna");
            contentPreferences.add("danza contemporánea");
            contentPreferences.add("contemporary ballet");
            contentPreferences.add("danza clásica india");
            contentPreferences.add("danza clásica china");
            contentPreferences.add("danza folclórica mexicana");
            contentPreferences.add("danza folclórica española");
            contentPreferences.add("danza polinesia");
            contentPreferences.add("danza de salón");
            contentPreferences.add("danza deportiva");
            contentPreferences.add("danza terapia");
            contentPreferences.add("danza recreativa");
            contentPreferences.add("danza competitiva");
            contentPreferences.add("danza social");
            contentPreferences.add("danza comunitaria");



        }

        if (Boolean.parseBoolean(user.getContentPreference3())){
            contentPreferences.add("gastronomia");
            contentPreferences.add("cocina");
            contentPreferences.add("culinaria");
            contentPreferences.add("arte culinario");
            contentPreferences.add("gastronomía");
            contentPreferences.add("alimentación");
            contentPreferences.add("comida");
            contentPreferences.add("platos");
            contentPreferences.add("recetas");
            contentPreferences.add("sabores");
            contentPreferences.add("ingredientes");
            contentPreferences.add("cocinar");
            contentPreferences.add("chef");
            contentPreferences.add("cocción");
            contentPreferences.add("menú");
            contentPreferences.add("degustación");
            contentPreferences.add("preparación");
            contentPreferences.add("nutrición");
            contentPreferences.add("restaurantes");
            contentPreferences.add("gastronómico");
            contentPreferences.add("exquisito");
            contentPreferences.add("catering");
            contentPreferences.add("delicias culinarias");
            contentPreferences.add("bebidas");
            contentPreferences.add("postres");
            contentPreferences.add("aperitivos");
            contentPreferences.add("gourmet");
            contentPreferences.add("bufé");
            contentPreferences.add("cocina internacional");
            contentPreferences.add("cocina local");
            contentPreferences.add("cocina regional");
            contentPreferences.add("recetas de cocina");
            contentPreferences.add("platos típicos");
            contentPreferences.add("cultura gastronómica");
            contentPreferences.add("tradición culinaria");
            contentPreferences.add("restaurantes famosos");
            contentPreferences.add("cocina casera");
            contentPreferences.add("cocina de autor");
            contentPreferences.add("cocina saludable");
            contentPreferences.add("ingredientes frescos");
            contentPreferences.add("cocina étnica");
            contentPreferences.add("cocina gourmet");
            contentPreferences.add("cocina vegetariana");
            contentPreferences.add("cocina vegana");
            contentPreferences.add("cocina rápida");
            contentPreferences.add("cocina de temporada");
            contentPreferences.add("cocina fusion");
            contentPreferences.add("cocina molecular");
            contentPreferences.add("catas de vino");
            contentPreferences.add("maridaje");
            contentPreferences.add("degustación de platos");
            contentPreferences.add("menú degustación");
            contentPreferences.add("eventos gastronómicos");
            contentPreferences.add("ferias de comida");
            contentPreferences.add("festivales gastronómicos");
            contentPreferences.add("cursos de cocina");
            contentPreferences.add("escuelas de cocina");
            contentPreferences.add("revistas de cocina");
            contentPreferences.add("blogs de cocina");
            contentPreferences.add("programas de cocina");
            contentPreferences.add("sazón");
            contentPreferences.add("gastronomía mundial");
            contentPreferences.add("platos exóticos");
            contentPreferences.add("cocina de autor");
            contentPreferences.add("cocina mediterránea");
            contentPreferences.add("cocina asiática");
            contentPreferences.add("cocina mediterránea");
            contentPreferences.add("cocina japonesa");
            contentPreferences.add("cocina tailandesa");
            contentPreferences.add("cocina china");
            contentPreferences.add("cocina francesa");
            contentPreferences.add("cocina italiana");
            contentPreferences.add("cocina española");
            contentPreferences.add("cocina mexicana");
            contentPreferences.add("cocina peruana");
            contentPreferences.add("cocina brasileña");
            contentPreferences.add("cocina argentina");
            contentPreferences.add("cocina colombiana");
            contentPreferences.add("cocina venezolana");
            contentPreferences.add("cocina chilena");
            contentPreferences.add("cocina cubana");
            contentPreferences.add("cocina puertorriqueña");
            contentPreferences.add("cocina dominicana");
            contentPreferences.add("cocina centroamericana");
            contentPreferences.add("cocina caribeña");
            contentPreferences.add("cocina africana");
            contentPreferences.add("cocina árabe");
            contentPreferences.add("cocina india");
            contentPreferences.add("cocina tailandesa");
            contentPreferences.add("cocina vietnamita");
            contentPreferences.add("cocina coreana");
            contentPreferences.add("cocina filipina");
            contentPreferences.add("cocina australiana");
            contentPreferences.add("cocina vegetariana");
            contentPreferences.add("cocina vegana");
            contentPreferences.add("cocina sin gluten");
            contentPreferences.add("cocina sin lactosa");
            contentPreferences.add("cocina saludable");
            contentPreferences.add("cocina casera");
            contentPreferences.add("cocina rápida");
            contentPreferences.add("cocina gourmet");
            contentPreferences.add("cocina de alta cocina");
            contentPreferences.add("cocina de autor");
            contentPreferences.add("cocina fusión");
            contentPreferences.add("cocina molecular");
            contentPreferences.add("recetas saludables");
            contentPreferences.add("recetas fáciles");
            contentPreferences.add("recetas rápidas");
            contentPreferences.add("recetas caseras");
            contentPreferences.add("recetas tradicionales");
            contentPreferences.add("recetas innovadoras");
            contentPreferences.add("maridaje de vinos");
            contentPreferences.add("maridaje de cervezas");
            contentPreferences.add("maridaje de licores");
            contentPreferences.add("maridaje de quesos");
            contentPreferences.add("maridaje de chocolates");
            contentPreferences.add("maridaje de postres");



        }

        if (Boolean.parseBoolean(user.getContentPreference4())){
            contentPreferences.add("artisticas");
            contentPreferences.add("creativas");
            contentPreferences.add("artes");
            contentPreferences.add("expresivas");
            contentPreferences.add("imaginativas");
            contentPreferences.add("creadoras");
            contentPreferences.add("estéticas");
            contentPreferences.add("culturales");
            contentPreferences.add("bellas");
            contentPreferences.add("plásticas");
            contentPreferences.add("visuales");
            contentPreferences.add("escénicas");
            contentPreferences.add("literarias");
            contentPreferences.add("gráficas");
            contentPreferences.add("musicales");
            contentPreferences.add("cinematográficas");
            contentPreferences.add("performáticas");
            contentPreferences.add("audiovisuales");
            contentPreferences.add("pictóricas");
            contentPreferences.add("escultóricas");
            contentPreferences.add("teatrales");
            contentPreferences.add("coreográficas");
            contentPreferences.add("cinematográficas");
            contentPreferences.add("fotográficas");
            contentPreferences.add("poéticas");
            contentPreferences.add("abstracciones");
            contentPreferences.add("figurativas");
            contentPreferences.add("vanguardistas");
            contentPreferences.add("contemporáneas");
            contentPreferences.add("clásicas");
            contentPreferences.add("arte");
            contentPreferences.add("creatividad");
            contentPreferences.add("cultura");
            contentPreferences.add("expresión");
            contentPreferences.add("arte visual");
            contentPreferences.add("arte escénico");
            contentPreferences.add("arte plástico");
            contentPreferences.add("arte contemporáneo");
            contentPreferences.add("diseño");
            contentPreferences.add("música");
            contentPreferences.add("literatura");
            contentPreferences.add("pintura");
            contentPreferences.add("escultura");
            contentPreferences.add("arquitectura");
            contentPreferences.add("cine");
            contentPreferences.add("fotografía");
            contentPreferences.add("teatro");
            contentPreferences.add("danza");
            contentPreferences.add("performance");
            contentPreferences.add("instalación");
            contentPreferences.add("grabado");
            contentPreferences.add("arte digital");
            contentPreferences.add("bellas artes");
            contentPreferences.add("arte urbano");
            contentPreferences.add("arte abstracto");
            contentPreferences.add("arte figurativo");
            contentPreferences.add("arte conceptual");
            contentPreferences.add("arte cinético");
            contentPreferences.add("creativas");
            contentPreferences.add("expresivas");
            contentPreferences.add("imaginativas");
            contentPreferences.add("creadoras");
            contentPreferences.add("estéticas");
            contentPreferences.add("culturales");
            contentPreferences.add("bellas");
            contentPreferences.add("plásticas");
            contentPreferences.add("visuales");
            contentPreferences.add("escénicas");
            contentPreferences.add("literarias");
            contentPreferences.add("gráficas");
            contentPreferences.add("performáticas");
            contentPreferences.add("audiovisuales");
            contentPreferences.add("pictóricas");
            contentPreferences.add("escultóricas");
            contentPreferences.add("teatrales");
            contentPreferences.add("coreográficas");
            contentPreferences.add("fotográficas");
            contentPreferences.add("poéticas");
            contentPreferences.add("abstracciones");
            contentPreferences.add("figurativas");
            contentPreferences.add("vanguardistas");
            contentPreferences.add("contemporáneas");
            contentPreferences.add("diseño");
            contentPreferences.add("arquitectura");
            contentPreferences.add("instalación");
            contentPreferences.add("grabado");
            contentPreferences.add("arte digital");
            contentPreferences.add("bellas artes");
            contentPreferences.add("arte urbano");
            contentPreferences.add("arte abstracto");
            contentPreferences.add("arte figurativo");
            contentPreferences.add("arte conceptual");
            contentPreferences.add("arte cinético");
            contentPreferences.add("obra de arte");
            contentPreferences.add("creatividad");
            contentPreferences.add("cultura");
            contentPreferences.add("expresión");
            contentPreferences.add("arte visual");
            contentPreferences.add("arte escénico");
            contentPreferences.add("arte plástico");
            contentPreferences.add("arte contemporáneo");
            contentPreferences.add("música");
            contentPreferences.add("literatura");
            contentPreferences.add("pintura");
            contentPreferences.add("escultura");
            contentPreferences.add("cine");
            contentPreferences.add("teatro");
            contentPreferences.add("performance");
            contentPreferences.add("fotografía");
            contentPreferences.add("danza");
            contentPreferences.add("arte gráfico");
            contentPreferences.add("arte sonoro");
            contentPreferences.add("arte efímero");
            contentPreferences.add("arte interactivo");
            contentPreferences.add("arte público");
            contentPreferences.add("arte corporal");
            contentPreferences.add("arte ritual");
            contentPreferences.add("arte ritual");
            contentPreferences.add("arte conceptual");
            contentPreferences.add("arte cinético");



        }

        if (Boolean.parseBoolean(user.getContentPreference5())){
            contentPreferences.add("tradiciones");
            contentPreferences.add("costumbres");
            contentPreferences.add("usos");
            contentPreferences.add("rituales");
            contentPreferences.add("folclore");
            contentPreferences.add("legado cultural");
            contentPreferences.add("herencia");
            contentPreferences.add("prácticas");
            contentPreferences.add("ceremonias");
            contentPreferences.add("ritos");
            contentPreferences.add("festividades");
            contentPreferences.add("celebraciones");
            contentPreferences.add("fiestas");
            contentPreferences.add("eventos culturales");
            contentPreferences.add("costumbres ancestrales");
            contentPreferences.add("cultura popular");
            contentPreferences.add("tradiciones familiares");
            contentPreferences.add("creencias");
            contentPreferences.add("rituales religiosos");
            contentPreferences.add("rituales sociales");
            contentPreferences.add("historia oral");
            contentPreferences.add("leyendas");
            contentPreferences.add("relatos");
            contentPreferences.add("mitos");
            contentPreferences.add("sabiduría ancestral");
            contentPreferences.add("folklore");
            contentPreferences.add("patrimonio cultural");
            contentPreferences.add("prácticas ancestrales");
            contentPreferences.add("usanzas");
            contentPreferences.add("convenciones sociales");
            contentPreferences.add("tradiciones culturales");
            contentPreferences.add("patrimonio intangible");
            contentPreferences.add("costumbres locales");
            contentPreferences.add("festivales tradicionales");
            contentPreferences.add("celebraciones regionales");
            contentPreferences.add("rituales comunitarios");
            contentPreferences.add("fiestas populares");
            contentPreferences.add("ceremonias ceremoniales");
            contentPreferences.add("rituales religiosos");
            contentPreferences.add("tradiciones familiares");
            contentPreferences.add("folklore regional");
            contentPreferences.add("historias locales");
            contentPreferences.add("mitología");
            contentPreferences.add("cuentos tradicionales");
            contentPreferences.add("música folclórica");
            contentPreferences.add("bailes tradicionales");
            contentPreferences.add("vestimenta típica");
            contentPreferences.add("gastronomía tradicional");
            contentPreferences.add("arte popular");
            contentPreferences.add("artesanía regional");
            contentPreferences.add("literatura oral");
            contentPreferences.add("mitos y leyendas");
            contentPreferences.add("usos y costumbres");
            contentPreferences.add("patrimonio inmaterial");
            contentPreferences.add("cultura viva");
            contentPreferences.add("identidad cultural");
            contentPreferences.add("conservación del patrimonio");
            contentPreferences.add("transmisión oral");
            contentPreferences.add("preservación cultural");
            contentPreferences.add("usos");
            contentPreferences.add("herencia");
            contentPreferences.add("prácticas");
            contentPreferences.add("ceremonias");
            contentPreferences.add("ritos");
            contentPreferences.add("festividades");
            contentPreferences.add("celebraciones");
            contentPreferences.add("fiestas");
            contentPreferences.add("eventos culturales");
            contentPreferences.add("costumbres ancestrales");
            contentPreferences.add("cultura popular");
            contentPreferences.add("creencias");
            contentPreferences.add("rituales religiosos");
            contentPreferences.add("historia oral");
            contentPreferences.add("leyendas");
            contentPreferences.add("relatos");
            contentPreferences.add("mitos");
            contentPreferences.add("sabiduría ancestral");
            contentPreferences.add("folklore");
            contentPreferences.add("patrimonio cultural");
            contentPreferences.add("prácticas ancestrales");
            contentPreferences.add("usanzas");
            contentPreferences.add("convenciones sociales");
            contentPreferences.add("tradiciones culturales");
            contentPreferences.add("patrimonio intangible");
            contentPreferences.add("costumbres locales");
            contentPreferences.add("festivales tradicionales");
            contentPreferences.add("celebraciones regionales");
            contentPreferences.add("rituales comunitarios");
            contentPreferences.add("fiestas populares");
            contentPreferences.add("ceremonias ceremoniales");
            contentPreferences.add("folklore regional");
            contentPreferences.add("historias locales");
            contentPreferences.add("mitología");
            contentPreferences.add("cuentos tradicionales");
            contentPreferences.add("música folclórica");
            contentPreferences.add("bailes tradicionales");
            contentPreferences.add("vestimenta típica");
            contentPreferences.add("gastronomía tradicional");
            contentPreferences.add("arte popular");
            contentPreferences.add("artesanía regional");
            contentPreferences.add("literatura oral");
            contentPreferences.add("mitos y leyendas");
            contentPreferences.add("usos y costumbres");
            contentPreferences.add("patrimonio inmaterial");
            contentPreferences.add("cultura viva");
            contentPreferences.add("identidad cultural");
            contentPreferences.add("conservación del patrimonio");
            contentPreferences.add("transmisión oral");
            contentPreferences.add("preservación cultural");
            contentPreferences.add("costumbres regionales");
            contentPreferences.add("tradiciones locales");
            contentPreferences.add("tradiciones ancestrales");
            contentPreferences.add("costumbres folclóricas");
            contentPreferences.add("tradiciones religiosas");
            contentPreferences.add("rituales culturales");
            contentPreferences.add("celebraciones tradicionales");

        }

        Collections.reverse(tweets);

        List<TweetResponseDto> prioritizedTweets = tweets.stream()
                .filter(tweet -> containsAnyKeyword(tweet.getTweetText(), contentPreferences))
                .collect(Collectors.toList());


        tweets.removeAll(prioritizedTweets);
        prioritizedTweets.addAll(tweets);

        prioritizedTweets = prioritizedTweets.stream()
                .filter(tweet -> Integer.parseInt(tweet.getDuration()) < 15)
                .collect(Collectors.toList());

        return ResponseEntity.ok(prioritizedTweets);
    }

    private boolean containsAnyKeyword(String text, List<String> keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/replies-for-tweet/{id}")
    public ResponseEntity<List<TweetResponseDto>> getRepliesForTweet(@PathVariable(name = "id") Long id){
        List<TweetResponseDto> tweets = this.tweetService.getRepliesForTweet(id);
        return ResponseEntity.ok(tweets);
    }
}

