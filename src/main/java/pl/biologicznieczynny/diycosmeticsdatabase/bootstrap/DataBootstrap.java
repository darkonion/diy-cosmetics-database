package pl.biologicznieczynny.diycosmeticsdatabase.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Difficulty;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Ingredient;
import pl.biologicznieczynny.diycosmeticsdatabase.models.IngredientQuantity;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Recipe;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Step;
import pl.biologicznieczynny.diycosmeticsdatabase.models.Tool;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.IngredientRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeCategoryRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.RecipeRepository;
import pl.biologicznieczynny.diycosmeticsdatabase.repositories.UnitOfMeasureRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeCategoryRepository recipeCategoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataBootstrap(IngredientRepository ingredientRepository,
            RecipeRepository recipeRepository,
            RecipeCategoryRepository recipeCategoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipeCategoryRepository = recipeCategoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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

        Ingredient linenOil = new Ingredient();
        linenOil.setName("Olej Lniany");
        linenOil.setDescription("Poprzez tłoczenie na zimno nasion lnu zwyczajnego otrzymywany jest olej roślinny o żółtawym zabarwieniu i intensywnym, cierpkim zapachu. Znany już w starożytnym Egipcie. Współcześnie wykorzystywany do celów spożywczych i przemysłowych.");
        linenOil.setImageUrl("https://cdn.galleries.smcloud.net/t/galleries/gf-YQxP-8qMS-t4bC_olej-lniany-obniza-cholesterol-664x442-nocrop.jpg");

        Ingredient etoh = new Ingredient();
        etoh.setName("EtOH");
        etoh.setDescription("W temperaturze pokojowej jest bezbarwną, łatwopalną cieczą o swoistym zapachu i piekącym smaku. W obecności powietrza pali się ona słabo widocznym, niebieskawym płomieniem.");
        etoh.setBlogUrl("Http://biologicznieczynny.com/etoh");

        Ingredient sheaButter = new Ingredient();
        sheaButter.setName("Masło Shea");
        sheaButter.setDescription("Olej roślinny uzyskiwany z owoców masłosza Parka. W postaci świeżej, nieprzetworzonej ma konsystencję pasty, barwę białawą, jest niemal bezwonny i ma bardzo słabo wyczuwalny smak.cznym, niebieskawym płomieniem.");
        sheaButter.setBlogUrl("Http://biologicznieczynny.com/etoh");

        Ingredient glycerin = new Ingredient();
        glycerin.setName("Gliceryna");
        glycerin.setDescription("Olej roślinny uzyskiwany z owoców masłosza Parka. W postaci świeżej, nieprzetworzonej ma konsystencję pasty, barwę białawą, jest niemal bezwonny i ma bardzo słabo wyczuwalny smak.cznym, niebieskawym płomieniem.");
        glycerin.setImageUrl("https://zasoby.ekologia.pl/artykulyNew/24967/xxl/1_800x600.jpg");

        Ingredient propyleneGlycol = new Ingredient();
        propyleneGlycol.setName("Glikol Propylenowy");
        propyleneGlycol.setDescription("Olej roślinny uzyskiwany z owoców masłosza Parka. W postaci świeżej, nieprzetworzonej ma konsystencję pasty, barwę białawą, jest niemal bezwonny i ma bardzo słabo wyczuwalny smak.cznym, niebieskawym płomieniem.");
        propyleneGlycol.setBlogUrl("Http://biologicznieczynny.com/etoh");


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

        IngredientQuantity glycerinQuantity = new IngredientQuantity();
        glycerinQuantity.setAmount(3);
        glycerinQuantity.setIngredient(glycerin);
        glycerinQuantity.setUnitOfMeasure(unitOfMeasureRepository.findByUom("Łyżki stołowe").get());

        Set<IngredientQuantity> ingredientQuantities = new HashSet<>();
        ingredientQuantities.add(oilQuantity);
        ingredientQuantities.add(waterQuantity);
        ingredientQuantities.add(glycerinQuantity);

        Tool mixer = new Tool();
        mixer.setName("Mikser");
        mixer.setDescription("Prosty kuchenny misker");

        Tool spoon = new Tool();
        spoon.setName("Łyżka");
        spoon.setDescription("Metalowa łyżka do mieszania");

        Tool jewelryWeight = new Tool();
        jewelryWeight.setName("Waga Jubilerska");
        jewelryWeight.setDescription("Zwykła waga jubilerska, do nabycia w smartshopach albo na allegro");

        Tool cylinder = new Tool();
        cylinder.setName("Cylinder miarowy 10ml");
        cylinder.setDescription("Cylinder miarowy o pojemności 10ml, im mniejszy tym lepszy");

        Step first = new Step();
        first.setName("First Step Here");
        first.setDetail("Just a first step, mix it and thats it");

        Step second = new Step();
        second.setName("Second Step Here");
        second.setDetail("Just a second step, mix it and thats it");



        Recipe bodyCream = new Recipe();
        bodyCream.setPrepTime(200);
        bodyCream.setName("Krem do ciała");
        bodyCream.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        bodyCream.setDifficulty(Difficulty.MODERATE);
        bodyCream.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2018/08/aroma-care-close-up-725998-1.jpg");
        bodyCream.setCategories(Set.of(recipeCategoryRepository.findById(1L).get(), recipeCategoryRepository.findById(3L).get()));
        bodyCream.setIntro("Prosty w wykonaniu krem do ciała ujędrniający i odmładzający skórę.");
        recipeRepository.save(bodyCream);

        Recipe faceCream = new Recipe();
        faceCream.setPrepTime(300);
        faceCream.setName("Malinowy krem do Twarzy");
        faceCream.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        faceCream.setDifficulty(Difficulty.HARD);
        faceCream.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/09/2-1.jpg");
        faceCream.setCategories(Set.of(recipeCategoryRepository.findById(1L).get(), recipeCategoryRepository.findById(3L).get()));
        faceCream.setIntro("Zaawansowany krem do twarzy na noc, pozytywne efekty już po 3 dniach stosowania! ");
        recipeRepository.save(faceCream);

        Recipe headShampoo = new Recipe();
        headShampoo.setPrepTime(150);
        headShampoo.setName("Pokrzywowy Szampon");
        headShampoo.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        headShampoo.setDifficulty(Difficulty.MODERATE);
        headShampoo.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/07/3.jpg");
        headShampoo.setCategories(Set.of(recipeCategoryRepository.findById(2L).get()));
        headShampoo.setIntro("Odświeżający szampon do włosów, w składzie min. pokrzywa, szauwia i olej kokosowy.");
        recipeRepository.save(headShampoo);

        Recipe waterSoap = new Recipe();
        waterSoap.setPrepTime(150);
        waterSoap.setName("Skuteczne mydło do rąk");
        waterSoap.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        waterSoap.setDifficulty(Difficulty.MODERATE);
        waterSoap.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/07/hoa1.jpg");
        waterSoap.setCategories(Set.of(recipeCategoryRepository.findById(1L).get()));
        waterSoap.setIntro("Mydło do dłoni niedrażniące skóry, skuteczne w walce z COVID");
        recipeRepository.save(waterSoap);

        Recipe faceSerum = new Recipe();
        faceSerum.setPrepTime(500);
        faceSerum.setName("Serum do twarzy");
        faceSerum.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        faceSerum.setDifficulty(Difficulty.HARD);
        faceSerum.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2019/06/7.jpg");
        faceSerum.setCategories(Set.of(recipeCategoryRepository.findById(1L).get()));
        faceSerum.setIntro("Serum do twarzy, nie wiem co robi, bo nie mam pojęcia czym jest serum");
        recipeRepository.save(faceSerum);

        Recipe oilBalm = new Recipe();
        oilBalm.setPrepTime(100);
        oilBalm.setName("Olejek do ciała");
        oilBalm.setDescription("W potocznym i marketingowym języku słowo \"olejek\" używane jest niewłaściwie do określenia kosmetyków składających się z rozpuszczalnych w tłuszczach (lipofilowych) składników. W kosmetologii nazwa olejki zarezerwowana jest dla olejków eterycznych (lotnych destylatów z roślin). Z kolei kosmetyki składające się z lipidowych składników to po prostu oliwki. Zrobienie oliwki zajmie Ci zaledwie kilka minut. Ważne, aby poznać kilka zasad łączenia składników, poza tym będziesz mogła tworzyć własne kosmetyki według preferencji i pomysłowości oraz jakości porównywalnej do najlepszych kosmetyków na rynku.");
        oilBalm.setDifficulty(Difficulty.EASY);
        oilBalm.setImageUrl("http://biologicznieczynny.pl/wp-content/uploads/2020/02/6.jpg");
        oilBalm.setCategories(Set.of(recipeCategoryRepository.findById(1L).get(), recipeCategoryRepository.findById(3L).get()));
        oilBalm.setIntro("Olejek do ciała dla leniwych, tylko trzy składniki! :) ");


        oilBalm.setTools(Set.of(spoon, mixer));

        waterQuantity.setRecipe(oilBalm);
        oilQuantity.setRecipe(oilBalm);
        glycerinQuantity.setRecipe(oilBalm);
        oilBalm.setIngredientQuantities(ingredientQuantities);

        first.setRecipe(oilBalm);
        second.setRecipe(oilBalm);
        oilBalm.setSteps(Set.of(first, second));

        recipeRepository.save(oilBalm);
    }
}
