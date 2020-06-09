package pl.biologicznieczynny.diycosmeticsdatabase.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Difficulty;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.IngredientQuantityRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.IngredientRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeCategoryRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.ToolRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static pl.biologicznieczynny.diycosmeticsdatabase.models.Difficulty.EASY;

@Profile("firstrun")
@Component
@Slf4j
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final ToolRepository toolRepository;
    private final IngredientQuantityRepository ingredientQuantityRepository;

    public DataBootstrap(IngredientRepository ingredientRepository,
            RecipeRepository recipeRepository,
            RecipeCategoryRepository recipeCategoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository,
            ToolRepository toolRepository,
            IngredientQuantityRepository ingredientQuantityRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.toolRepository = toolRepository;
        this.ingredientQuantityRepository = ingredientQuantityRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Bootstrapping data in progress...");

        bootstrapData();
    }

    @Transactional
    void bootstrapData() {

        //Creating Ingredients
        Ingredient water = new Ingredient();
        water.setName("Woda");
        water.setDescription("Woda swiatlo hemoglobina");
        water.setImageUrl("https://i2.wp.com/eduzabawy.com/wp-content/uploads/2017/11/woda.jpg");
        water.setLatinName("H2O");
        water.setBlogUrl("http://biologicznieczynny.pl/encyclopedia/");

        Ingredient linenOil = new Ingredient();
        linenOil.setName("Olej Lniany");
        linenOil.setDescription("Poprzez tłoczenie na zimno nasion lnu zwyczajnego otrzymywany jest olej roślinny o żółtawym zabarwieniu i intensywnym, cierpkim zapachu. Znany już w starożytnym Egipcie. Współcześnie wykorzystywany do celów spożywczych i przemysłowych.");
        linenOil.setImageUrl("https://cdn.galleries.smcloud.net/t/galleries/gf-YQxP-8qMS-t4bC_olej-lniany-obniza-cholesterol-664x442-nocrop.jpg");
        linenOil.setLatinName("Linen Oil");
        linenOil.setBlogUrl("http://biologicznieczynny.pl/encyclopedia/");

        Ingredient etoh = new Ingredient();
        etoh.setName("Etanol");
        etoh.setDescription("W temperaturze pokojowej jest bezbarwną, łatwopalną cieczą o swoistym zapachu i piekącym smaku. W obecności powietrza pali się ona słabo widocznym, niebieskawym płomieniem.");
        etoh.setImageUrl("https://thumbs.dreamstime.com/b/etanol-w-butelki-ikonie-mieszkanie-styl-79030851.jpg");
        etoh.setLatinName("EtOH");
        etoh.setBlogUrl("http://biologicznieczynny.pl/encyclopedia/");

        Ingredient sheaButter = new Ingredient();
        sheaButter.setName("Masło Shea");
        sheaButter.setDescription("Olej roślinny uzyskiwany z owoców masłosza Parka. W postaci świeżej, nieprzetworzonej ma konsystencję pasty, barwę białawą, jest niemal bezwonny i ma bardzo słabo wyczuwalny smak.cznym, niebieskawym płomieniem.");
        sheaButter.setImageUrl("https://www.gracefruit.com/uploads/images/products/large/gracefruit_gracefruit_organicunrefinedsheabutter_1460545985New_pic_1.jpg");
        sheaButter.setLatinName("Vitellaria paradoxa");
        sheaButter.setBlogUrl("http://biologicznieczynny.pl/encyclopedia/");

        Ingredient glycerin = new Ingredient();
        glycerin.setName("Gliceryna");
        glycerin.setDescription("Olej roślinny uzyskiwany z owoców masłosza Parka. W postaci świeżej, nieprzetworzonej ma konsystencję pasty, barwę białawą, jest niemal bezwonny i ma bardzo słabo wyczuwalny smak.cznym, niebieskawym płomieniem.");
        glycerin.setImageUrl("https://zasoby.ekologia.pl/artykulyNew/24967/xxl/1_800x600.jpg");
        glycerin.setLatinName("glycerine");
        glycerin.setBlogUrl("http://biologicznieczynny.pl/encyclopedia/");

        Ingredient propyleneGlycol = new Ingredient();
        propyleneGlycol.setName("Glikol Propylenowy");
        propyleneGlycol.setDescription("Olej roślinny uzyskiwany z owoców masłosza Parka. W postaci świeżej, nieprzetworzonej ma konsystencję pasty, barwę białawą, jest niemal bezwonny i ma bardzo słabo wyczuwalny smak.cznym, niebieskawym płomieniem.");
        propyleneGlycol.setImageUrl("https://procold.pl/wp-content/uploads/2019/04/Depositphotos_6800377_ds-e1554878919448.jpg");
        propyleneGlycol.setLatinName("propylene-1,3-diol");
        propyleneGlycol.setBlogUrl("http://biologicznieczynny.pl/encyclopedia/");

        ingredientRepository.save(water);
        ingredientRepository.save(linenOil);
        ingredientRepository.save(etoh);
        ingredientRepository.save(glycerin);
        ingredientRepository.save(sheaButter);
        ingredientRepository.save(propyleneGlycol);

        IngredientQuantity waterQuantity = new IngredientQuantity();
        waterQuantity.setAmount(100);
        waterQuantity.setIngredient(water);
        waterQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("ml").get());

        IngredientQuantity oilQuantity = new IngredientQuantity();
        oilQuantity.setAmount(80);
        oilQuantity.setIngredient(linenOil);
        oilQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("ml").get());

        IngredientQuantity linenOilQuantity = new IngredientQuantity();
        linenOilQuantity.setAmount(5);
        linenOilQuantity.setIngredient(linenOil);
        linenOilQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("ml").get());

        IngredientQuantity glycerinQuantity = new IngredientQuantity();
        glycerinQuantity.setAmount(3);
        glycerinQuantity.setIngredient(glycerin);
        glycerinQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("Łyżki stołowe").get());

        IngredientQuantity sheaQuantity = new IngredientQuantity();
        sheaQuantity.setAmount(25);
        sheaQuantity.setIngredient(sheaButter);
        sheaQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("g").get());

        IngredientQuantity glycolQuantity = new IngredientQuantity();
        glycolQuantity.setAmount(50);
        glycolQuantity.setIngredient(propyleneGlycol);
        glycolQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("ml").get());

        IngredientQuantity etohQuantity = new IngredientQuantity();
        etohQuantity.setAmount(50);
        etohQuantity.setIngredient(etoh);
        etohQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("ml").get());

        Set<IngredientQuantity> ingredientQuantities = new HashSet<>();
        ingredientQuantities.add(oilQuantity);
        ingredientQuantities.add(waterQuantity);
        ingredientQuantities.add(glycerinQuantity);

        Set<IngredientQuantity> ingredientQuantities2 = new HashSet<>();

        ingredientQuantities2.add(sheaQuantity);
        ingredientQuantities2.add(glycolQuantity);
        ingredientQuantities2.add(etohQuantity);
        ingredientQuantities2.add(linenOilQuantity);

        Tool mixer = new Tool();
        mixer.setName("Mikser");
        mixer.setDescription("Prosty kuchenny misker");
        toolRepository.save(mixer);

        Tool spoon = new Tool();
        spoon.setName("Łyżka");
        spoon.setDescription("Metalowa łyżka do mieszania");
        toolRepository.save(spoon);

        Tool jewelryWeight = new Tool();
        jewelryWeight.setName("Waga Jubilerska");
        jewelryWeight.setDescription("Zwykła waga jubilerska, do nabycia w smartshopach albo na allegro");
        toolRepository.save(jewelryWeight);

        Tool cylinder = new Tool();
        cylinder.setName("Cylinder miarowy 10ml");
        cylinder.setDescription("Cylinder miarowy o pojemności 10ml, im mniejszy tym lepszy");
        toolRepository.save(cylinder);

        Step first = new Step();
        first.setName("Krok pierwszy - przygotowanie składników");
        first.setSeq(1);
        first.setDetail("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");


        Step second = new Step();
        second.setName("Krok drugi - wymieszanie składników w naczyniu");
        second.setSeq(2);
        second.setDetail("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        Step third = new Step();
        third.setName("Krok trzeci - podgrzewanie roztworu");
        third.setSeq(3);
        third.setDetail("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        Step first2 = new Step();
        first2.setName("Krok pierwszy - przygotowanie składników i pomiar");
        first2.setSeq(1);
        first2.setDetail("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        Step second2 = new Step();
        second2.setName("Krok drugi - wymieszanie składników w naczyniu");
        second2.setSeq(2);
        second2.setDetail("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        Step third2 = new Step();
        third2.setName("Krok trzeci - podgrzewanie roztworu");
        third2.setSeq(3);
        third2.setDetail("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");


        Recipe bodyCream = new Recipe();
        bodyCream.setPrepTime(200);
        bodyCream.setName("Krem do ciała");
        bodyCream.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        bodyCream.setDifficulty(Difficulty.MODERATE);
        bodyCream.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2018/08/aroma-care-close-up-725998-1.jpg");
        bodyCream.setRecipeCategories(Set.of(recipeCategoryRepository.findById(1L).get(), recipeCategoryRepository.findById(3L).get()));
        bodyCream.setIntro("Prosty w wykonaniu krem do ciała ujędrniający i odmładzający skórę.");
        recipeRepository.save(bodyCream);

        Recipe faceCream = new Recipe();
        faceCream.setPrepTime(300);
        faceCream.setName("Malinowy krem do Twarzy");
        faceCream.setDescription("Istnieje wiele legend, bardziej miejskich, niż ludowych, które narosły wokół robienia kosmetyków w domu. Jedną z nich jest właśnie konieczność znajomości chemii. Nie musisz być ekspertką w rozrysowywaniu wiązań chemicznych, żeby ukręcić samodzielnie krem. Wystarczy, że poświęcisz trochę czasu na poczytanie o właściwościach poszczególnych składników. To trochę tak jak z wiedzą o odżywianiu - możesz zdrowo jeść przestrzegając z grubsza proporcji zawartych w piramidzie żywienia, świadomość wszystkich złożonych procesów wywoływanych w organizmie przez chleb pszenny i pomidory nie jest niezbędna.\n" +
                "Używanie kremów ekologicznych nie jest poświęceniem i przestawieniem się na gorsze produkty. Ekologia stanowi raczej miły dodatek - pięknieje twoja skóra i pięknieje świat. Podstawowa przewaga takich kremów nad tymi drogeryjnymi jest taka, że doskonale wiemy, co jest w naszym słoiczku. Firmy kosmetyczne kuszą nas koenzymem Q10, a reklamowany w ten sposób kosmetyk może zawierać śladowe ilości substancji, niewyczuwalne dla skóry. Składy na opakowaniach są podawane wedle zasady \"od największej do najmniejszej zawartości\", wyszczególnienia procentowe należą do rzadkości. Zakraplając witaminę E i olej arganowy do samodzielnie zrobionego kremu widzimy za co płacimy. Co więcej, składy drogeryjnych specyfików są standaryzowane.");
        faceCream.setDifficulty(Difficulty.HARD);
        faceCream.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/09/2-1.jpg");
        faceCream.setRecipeCategories(Set.of(recipeCategoryRepository.findById(1L).get(), recipeCategoryRepository.findById(3L).get()));
        faceCream.setIntro("Zaawansowany krem do twarzy na noc, pozytywne efekty już po 3 dniach stosowania! ");

        linenOilQuantity.setRecipe(faceCream);
        sheaQuantity.setRecipe(faceCream);
        etohQuantity.setRecipe(faceCream);
        glycolQuantity.setRecipe(faceCream);
        faceCream.setIngredientQuantities(ingredientQuantities2);

        faceCream.setTools(Set.of(cylinder, mixer, jewelryWeight));

        first2.setRecipe(faceCream);
        second2.setRecipe(faceCream);
        third2.setRecipe(faceCream);
        faceCream.setSteps(Set.of(first2, second2, third2));
        recipeRepository.save(faceCream);

        Recipe headShampoo = new Recipe();
        headShampoo.setPrepTime(150);
        headShampoo.setName("Pokrzywowy Szampon");
        headShampoo.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        headShampoo.setDifficulty(Difficulty.MODERATE);
        headShampoo.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/07/3.jpg");
        headShampoo.setRecipeCategories(Set.of(recipeCategoryRepository.findById(2L).get()));
        headShampoo.setIntro("Odświeżający szampon do włosów, w składzie min. pokrzywa, szauwia i olej kokosowy.");
        recipeRepository.save(headShampoo);

        Recipe waterSoap = new Recipe();
        waterSoap.setPrepTime(150);
        waterSoap.setName("Skuteczne mydło do rąk");
        waterSoap.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        waterSoap.setDifficulty(Difficulty.MODERATE);
        waterSoap.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/07/hoa1.jpg");
        waterSoap.setRecipeCategories(Set.of(recipeCategoryRepository.findById(1L).get()));
        waterSoap.setIntro("Mydło do dłoni niedrażniące skóry, skuteczne w walce z COVID");
        recipeRepository.save(waterSoap);

        Recipe faceSerum = new Recipe();
        faceSerum.setPrepTime(500);
        faceSerum.setName("Serum do twarzy");
        faceSerum.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        faceSerum.setDifficulty(Difficulty.HARD);
        faceSerum.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/06/7.jpg");
        faceSerum.setRecipeCategories(Set.of(recipeCategoryRepository.findById(1L).get()));
        faceSerum.setIntro("Serum do twarzy, nie wiem co robi, bo nie mam pojęcia czym jest serum");
        recipeRepository.save(faceSerum);

        Recipe perfumeOil = new Recipe();
        perfumeOil.setPrepTime(100);
        perfumeOil.setName("Olejek zapachowy");
        perfumeOil.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        perfumeOil.setDifficulty(Difficulty.EASY);
        perfumeOil.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/09/3.jpg");
        perfumeOil.setRecipeCategories(Set.of(recipeCategoryRepository.findById(3L).get()));
        perfumeOil.setIntro("Olejek zapachowy uwalniający zapach pod wpływem ciepła, idealny do relaksu w domowym SPA");
        recipeRepository.save(perfumeOil);

        Recipe oilBalm = new Recipe();
        oilBalm.setPrepTime(100);
        oilBalm.setName("Olejek do ciała");
        oilBalm.setDescription("W potocznym i marketingowym języku słowo \"olejek\" używane jest niewłaściwie do określenia kosmetyków składających się z rozpuszczalnych w tłuszczach (lipofilowych) składników. W kosmetologii nazwa olejki zarezerwowana jest dla olejków eterycznych (lotnych destylatów z roślin). Z kolei kosmetyki składające się z lipidowych składników to po prostu oliwki. Zrobienie oliwki zajmie Ci zaledwie kilka minut. Ważne, aby poznać kilka zasad łączenia składników, poza tym będziesz mogła tworzyć własne kosmetyki według preferencji i pomysłowości oraz jakości porównywalnej do najlepszych kosmetyków na rynku.");
        oilBalm.setDifficulty(EASY);
        oilBalm.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2020/02/6.jpg");
        oilBalm.setRecipeCategories(Set.of(recipeCategoryRepository.findById(1L).get(), recipeCategoryRepository.findById(3L).get()));
        oilBalm.setIntro("Aromatyczny olejek do ciała dla leniwych, tylko trzy składniki!");


        oilBalm.setTools(Set.of(spoon));

        waterQuantity.setRecipe(oilBalm);
        oilQuantity.setRecipe(oilBalm);
        glycerinQuantity.setRecipe(oilBalm);
        oilBalm.setIngredientQuantities(ingredientQuantities);

        first.setRecipe(oilBalm);
        second.setRecipe(oilBalm);
        third.setRecipe(oilBalm);
        oilBalm.setSteps(Set.of(first, second, third));

        recipeRepository.save(oilBalm);
    }
}
